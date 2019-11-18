package com.example.tune_kotlin.activities

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.tune_kotlin.R
import com.example.tune_kotlin.adapters.CustomPreferencesAdapter
import com.example.tune_kotlin.utils.Toolbar

class PreferencesActivity : AppCompatActivity() {

    private lateinit var preferencesListview: ListView
    private lateinit var btnSave: Button
    private lateinit var adapter: CustomPreferencesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        Toolbar(this, "preferences")

        val genres = intent.getStringArrayListExtra("PREFERENCES_GENRES")

        preferencesListview = findViewById(R.id.preferencesListview)
        adapter = CustomPreferencesAdapter(this, genres)
        preferencesListview.adapter = adapter

        btnSave = findViewById(R.id.btnSave)
        btnSave.setOnClickListener {
//            savePreferences()
        }
    }

//    private fun savePreferences(){
//        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
//        val reference: DatabaseReference = database.reference
//        val auth: FirebaseAuth = FirebaseAuth.getInstance()
//        val currentuser: FirebaseUser? = auth.currentUser
//        val preferences: ArrayList<String> = adapter.getPreferences()
//
//        reference.child("preferences/" + currentuser?.uid).removeValue()
//        reference.child("preferences/" + currentuser?.uid).setValue(preferences)
//        Toast.toast(this, "Preferences are saved!")
//        val intent = Intent(this, HomeActivity::class.java)
//        startActivity(intent)
//    }
}
