package com.example.tune_kotlin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.tune_kotlin.R
import com.example.tune_kotlin.utils.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        val currentuser = firebaseAuth.currentUser
        if (currentuser == null) {
            setContentView(R.layout.activity_main)

            btnLogin = findViewById(R.id.btnLogin)
            etEmail = findViewById(R.id.etEmail)
            etPassword = findViewById(R.id.etPassword)
            btnRegister = findViewById(R.id.btnRegister)



            btnLogin.setOnClickListener {
                login()
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

    private fun login() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(applicationContext, HomeActivity::class.java)
                    startActivity(intent)
                    Toast.toast(applicationContext, "Succesfully log in!")
                } else {
                    Toast.toast(applicationContext, "Authentication failed!")
                    println("ERROR:: " + task.exception!!.message)
                }
            }
    }
}
