package com.example.tune_kotlin.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tune_kotlin.R
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.storage.UploadTask
import com.google.android.gms.tasks.OnSuccessListener
import android.net.Uri
import android.widget.*
import com.example.tune_kotlin.models.Genre
import com.example.tune_kotlin.utils.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.StorageMetadata
import java.io.File
import com.google.firebase.database.FirebaseDatabase
import com.example.tune_kotlin.models.Post
import com.google.firebase.database.DatabaseReference


class UploadActivity : AppCompatActivity() {

    private lateinit var btnStart: ImageButton
    private lateinit var btnStop: ImageButton
    private lateinit var btnUpload: Button
    private lateinit var spinnerGenre: Spinner
    private lateinit var btnDate: Button
    private lateinit var etLocation: EditText
    private var filePath: String? = null
    private var player: MediaPlayer? = null
    private lateinit var storage: StorageReference
    private val datepickerTxt: String = "Click here to select date"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        filePath = intent.getStringExtra("FILE_PATH")

        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)
        btnUpload = findViewById(R.id.btnUpload)
        btnDate = findViewById(R.id.btnDate)
        spinnerGenre = findViewById(R.id.spinnerGenre)
        etLocation = findViewById(R.id.etLocation)

        btnStart.setOnClickListener{
            start()
        }

        btnStop.setOnClickListener{
            stop()
        }

        btnDate.setOnClickListener {
            showDatePicker()
        }

        btnUpload.setOnClickListener {
            uploadToStorage()
        }

        btnDate.text = datepickerTxt

        val genreAdapter = ArrayAdapter<Genre>(this, R.layout.spinner_item, Genre.values())
        spinnerGenre.adapter = genreAdapter
        spinnerGenre.getBackground().setColorFilter(BlendModeColorFilter(R.color.white, BlendMode.SRC_ATOP))

    }

    private fun uploadToStorage(){
        if (!etLocation.text.toString().equals("") && etLocation.text.toString() != "" && btnDate.text.toString() != "Click here to select date") {
            val genre: String = spinnerGenre.selectedItem.toString()

            storage = FirebaseStorage.getInstance().reference

            val file = File(filePath)
            val uri = Uri.fromFile(File(filePath))
            val currentuser: FirebaseUser? = getCurrentuser()

            val storageChild: String =
                genre + "/" + file.absoluteFile.name.replace(".mp3", "") + "::" + currentuser?.uid
            val riversRef = storage.child(storageChild)

            val metadata = StorageMetadata.Builder()
                .setContentType("audio/mpeg")
                .build()

            riversRef.putFile(uri, metadata)
                .addOnSuccessListener {
                    uploadToDatabase(currentuser, storageChild, genre)
                }
                .addOnFailureListener {
                    Toast.toast(this, "Failed upload!")
                }
        } else {
            Toast.toast(this, "Not all fields are filled in!")
        }
    }

    private fun uploadToDatabase(user: FirebaseUser?, filename: String, genreStr: String){
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val reference: DatabaseReference = database.reference

        val location: String = etLocation.text.toString()
        val date: String = btnDate.text.toString()
        val genre: Genre = Genre.valueOf(genreStr)
        val post = Post(location, user, filename, genre, date)
        reference.child(filename).setValue(post)

        Toast.toast(this, "Succesfully uploaded!")
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun start(){
        player = MediaPlayer().apply {
            try{
                setDataSource(filePath)
                prepare()
                start()
            } catch (e: IOException){
                println(e.message)
            }
        }
    }

    private fun stop(){
        player?.release()
        player = null
    }

    private fun showDatePicker(){

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dialog =
            DatePickerDialog(this, R.style.datePicker,
                DatePickerDialog.OnDateSetListener { datePicker, i, i1, i2 -> }, year, month, day
            )
        dialog.show()

        val btnOk = dialog.getButton(DatePickerDialog.BUTTON_POSITIVE) as Button
        btnOk.setOnClickListener {
            btnDate.text = dialog.datePicker.dayOfMonth.toString() + "-" + dialog.datePicker.month + "-" + dialog.datePicker.year
            dialog.dismiss()
        }
    }

    private fun getCurrentuser(): FirebaseUser?{
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.currentUser
        return currentUser
    }
}
