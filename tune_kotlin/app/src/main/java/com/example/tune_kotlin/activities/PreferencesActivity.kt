package com.example.tune_kotlin.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.tune_kotlin.R
import com.example.tune_kotlin.adapters.CustomPreferencesAdapter
import com.example.tune_kotlin.models.Genre
import com.example.tune_kotlin.utils.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class PreferencesActivity : AppCompatActivity() {

    private lateinit var preferencesListview: ListView
    private lateinit var btnSave: Button
    private lateinit var adapter: CustomPreferencesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val toolbar = findViewById<Toolbar>(R.id.defaultToolbar)
        val txtToolbar = findViewById<TextView>(R.id.textViewToolbar)
        txtToolbar.text = "preferences"
        setSupportActionBar(toolbar)

        preferencesListview = findViewById(R.id.preferencesListview)
        adapter = CustomPreferencesAdapter(this, Genre.values(), populatePrefrences())
        preferencesListview.adapter = adapter

        btnSave = findViewById(R.id.btnSave)
        btnSave.setOnClickListener {
            savePreferences()
        }
    }

    private fun populatePrefrences(): Array<Genre> {
        return arrayOf<Genre>(Genre.Country, Genre.Metal, Genre.Hiphop)
    }

    private fun savePreferences(){
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val reference: DatabaseReference = database.reference
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        val currentuser: FirebaseUser? = auth.currentUser
        val preferences: ArrayList<Genre> = adapter.getPreferences()

        reference.child("preferences/" + currentuser?.uid).removeValue()
        reference.child("preferences/" + currentuser?.uid).setValue(preferences)
        Toast.toast(this, "Preferences are saved!")
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}