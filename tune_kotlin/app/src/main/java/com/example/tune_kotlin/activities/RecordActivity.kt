package com.example.tune_kotlin.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.tune_kotlin.R
import java.util.*

class RecordActivity : AppCompatActivity() {

    private lateinit var btnStop: ImageButton
    private lateinit var btnStart: ImageButton
    private val recorder = MediaRecorder()
    private lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        btnStop = findViewById(R.id.btnStop)
        btnStart = findViewById(R.id.btnStart)

        filePath = getFilePath()

        btnStop.isEnabled = false

        btnStart.setOnClickListener {
            start()
        }

        btnStop.setOnClickListener {
            stop()
        }
    }

    private fun start() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) run {

            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.RECORD_AUDIO),
                0
            )

        } else {
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            recorder.setOutputFile(filePath)
            recorder.prepare()
            recorder.start()

            btnStop.isEnabled = true
            btnStart.isEnabled = false
        }
    }

    private fun stop() {
        recorder.stop()
        recorder.reset()
        recorder.release()


        btnStart.isEnabled = true
        btnStop.isEnabled = false

        val intent = Intent(this, UploadActivity::class.java)
        intent.putExtra("FILE_PATH", filePath)
        startActivity(intent)

    }

    private fun getFilePath(): String {
        val filePath = applicationContext.filesDir.absolutePath +
                DateFormat.format("/yyyy-MM-dd_kk-mm-ss", Date().time) + ".mp3"
        return filePath
    }
}
