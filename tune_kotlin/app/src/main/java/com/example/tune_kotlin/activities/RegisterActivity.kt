package com.example.tune_kotlin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.tune_kotlin.R
import com.example.tune_kotlin.data.FirebaseAuth
import com.example.tune_kotlin.utils.Toast
import com.example.tune_kotlin.utils.Toolbar
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    private lateinit var btnRegister: Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirm: EditText

    private val firebaseAuth: FirebaseAuth = FirebaseAuth()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Toolbar(this, "register")


        btnRegister = findViewById(R.id.btnRegister)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirm = findViewById(R.id.etConfirm)

        btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val email: String = etEmail.text.toString()
        val password: String = etPassword.text.toString()
        val confirm: String = etConfirm.text.toString()

        if (email != "" && password != "") {
            if (validateEmail(email)) {
                if (validatePassword(password)) {
                    if (password == confirm) {
                        if(firebaseAuth.saveToFirebase(email, password)){
                            Toast.toast(this, "You can login now!")
                        } else {
                            Toast.toast(this, "Something whent wrong!")
                        }
                    } else {
                        Toast.toast(this, "Passwordfields don't match!")
                    }
                } else {
                    Toast.toast(this, "Password doesn't match requirements!")
                }
            } else {
                Toast.toast(this, "Invalid email!")
            }
        } else {
            Toast.toast(this, "Not all fields are filled in!")
        }

    }

    private fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validatePassword(password: String): Boolean {
        val pattern = Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$")
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }
}
