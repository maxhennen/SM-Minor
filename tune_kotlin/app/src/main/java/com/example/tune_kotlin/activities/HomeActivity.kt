package com.example.tune_kotlin.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton

import com.example.tune_kotlin.R
//import com.example.tune_kotlin.activities.TimelineActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar = findViewById(R.id.defaultToolbar) as Toolbar
        setSupportActionBar(toolbar)

//        val btnTimeline = findViewById(R.id.homeTimeline) as ImageButton
//        btnTimeline.setOnClickListener {
//            val toRecord = Intent(this@HomeActivity, TimelineActivity::class.java)
//            startActivity(toRecord)
//        }

        val btnRecord = findViewById(R.id.homeRecord) as ImageButton
        btnRecord.setOnClickListener {
            val toRecord = Intent(this@HomeActivity, RecordActivity::class.java)
            startActivity(toRecord)
        }

        val btnDiscover = findViewById(R.id.homeDiscover) as ImageButton
        btnDiscover.setOnClickListener {
            val toRecord = Intent(this@HomeActivity, DiscoverActivity::class.java)
            startActivity(toRecord)
        }

        val btnPreferences = findViewById(R.id.homePreferences) as ImageButton
        btnPreferences.setOnClickListener {
            val toRecord = Intent(this@HomeActivity, PreferencesActivity::class.java)
            startActivity(toRecord)
        }

    }

}
