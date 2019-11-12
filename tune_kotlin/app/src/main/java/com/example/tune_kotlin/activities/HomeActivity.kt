package com.example.tune_kotlin.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.tune_kotlin.R
import com.example.tune_kotlin.data.Firebase


class HomeActivity : AppCompatActivity() {

    private val firebase = Firebase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar = findViewById<Toolbar>(R.id.defaultToolbar)
        setSupportActionBar(toolbar)

        var genres = firebase.getGenres()

        val btnTimeline = findViewById<ImageButton>(R.id.homeTimeline)
        btnTimeline.setOnClickListener {
            val intent = Intent(this, TimelineDiscoverActivity::class.java)
            intent.putExtra("IS_TIMELINE_ACTIVITY", true)
            intent.putStringArrayListExtra("TIMELINE_GENRES", genres)
            startActivity(intent)
        }

        val btnRecord = findViewById<ImageButton>(R.id.homeRecord)
        btnRecord.setOnClickListener {
            val toRecord = Intent(this@HomeActivity, RecordActivity::class.java)
            startActivity(toRecord)
        }

        val btnDiscover = findViewById<ImageButton>(R.id.homeDiscover)
        btnDiscover.setOnClickListener {
            val toDiscover = Intent(this@HomeActivity, DiscoverActivity::class.java)
            startActivity(toDiscover)
        }

        val btnPreferences = findViewById<ImageButton>(R.id.homePreferences)
        btnPreferences.setOnClickListener {
            val toPreferences = Intent(this@HomeActivity, PreferencesActivity::class.java)
            startActivity(toPreferences)
        }

    }

}
