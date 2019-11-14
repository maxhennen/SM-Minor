package com.example.tune_kotlin.data

import android.content.Context
import android.content.Intent
import com.example.tune_kotlin.activities.MainActivity
import com.example.tune_kotlin.utils.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseAuth(): Firebase() {

    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun login(email: String, password: String): FirebaseUser? {
        firebaseAuth.signInWithEmailAndPassword(email, password)
        return firebaseAuth.currentUser!!
    }

    fun saveToFirebase(email: String, password: String): Boolean {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).isSuccessful
    }
}
