package com.example.tune_kotlin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tune_kotlin.R
import com.example.tune_kotlin.utils.Toolbar

class DiscoverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)

        Toolbar(this, "discover")

    }
}
