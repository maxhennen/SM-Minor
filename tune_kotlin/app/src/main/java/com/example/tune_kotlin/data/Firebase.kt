package com.example.tune_kotlin.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

abstract class Firebase {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val currentUser: FirebaseUser? = auth.currentUser
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val reference: DatabaseReference = database.reference
}