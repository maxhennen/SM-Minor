package com.example.tune_kotlin.models

import android.provider.MediaStore
import com.google.firebase.auth.FirebaseUser

import java.io.Serializable
import java.lang.reflect.Constructor

class Post() :Serializable {
    var id: Long? = null
    var location: String? = null
    var user: FirebaseUser? = null
    var audio: MediaStore.Audio? = null
    var filename: String? = null
    var genre: Genre? = null
    var comments: List<Comment>? = null
    var date: String? = null

    constructor(location: String, user: FirebaseUser?, filename: String, genre: Genre, date: String): this(){
        this.location = location
        this.user = user
        this. filename = filename
        this.genre = genre
        this.date = date
    }
}