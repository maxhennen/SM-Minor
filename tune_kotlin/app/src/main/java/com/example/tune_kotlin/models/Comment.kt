package com.example.tune_kotlin.models

import com.google.firebase.auth.FirebaseUser
import java.io.Serializable

class Comment(val user: FirebaseUser, val content: String) : Serializable