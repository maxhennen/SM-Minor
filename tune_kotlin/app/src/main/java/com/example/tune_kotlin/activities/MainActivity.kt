package com.example.tune_kotlin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.tune_kotlin.R
import com.example.tune_kotlin.utils.Toast
import com.example.tune_kotlin.utils.Toolbar
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button
    private val firebaseAuth = com.example.tune_kotlin.data.FirebaseAuth()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentuser = firebaseAuth.currentUser
        if (currentuser == null) {
            setContentView(R.layout.activity_main)

            Toolbar(this, "tune")

            btnLogin = findViewById(R.id.btnLogin)
            etEmail = findViewById(R.id.etEmail)
            etPassword = findViewById(R.id.etPassword)
            btnRegister = findViewById(R.id.btnRegister)



            btnLogin.setOnClickListener {
                firebaseAuth.login(etEmail.text.toString(), etPassword.text.toString())
            }

            btnRegister.setOnClickListener {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        } else {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
