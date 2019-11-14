package com.example.tune_kotlin.utils

import android.content.Intent
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.tune_kotlin.R
import com.example.tune_kotlin.activities.ProfileActivity

class Toolbar(activity: AppCompatActivity, name: String) {
    init {
        val toolbar = activity.findViewById<Toolbar>(R.id.defaultToolbar)
        val textToolbar = activity.findViewById<TextView>(R.id.textViewToolbar)
        textToolbar.text = name

        val btnProfile = activity.findViewById<ImageButton>(R.id.btnLogout)
        btnProfile.setOnClickListener {

        }

        activity.setSupportActionBar(toolbar)
    }
}