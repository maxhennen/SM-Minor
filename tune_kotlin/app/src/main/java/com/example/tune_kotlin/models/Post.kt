package com.example.tune_kotlin.models

import java.io.File
import java.io.Serializable

class Post() :Serializable {
    var id: Long? = null
    var location: String? = null
    var email: String? = null
    var file: File? = null
    var filename: String? = null
    var genre: Genre? = null
    var comments: ArrayList<Comment>? = null
    var date: String? = null

    constructor(location: String, email: String?, filename: String, genre: Genre, date: String, comments: ArrayList<Comment>): this(){
        this.location = location
        this.email = email
        this. filename = filename
        this.genre = genre
        this.date = date
        this.comments = comments
    }

    constructor(location: String, email: String?, filename: String, genre: Genre, date: String): this(){
        this.location = location
        this.email = email
        this. filename = filename
        this.genre = genre
        this.date = date
    }


    override fun toString(): String {
        return "Post(id=$id, location=$location, email=$email, file=$file, filename=$filename, genre=$genre, comments=$comments, date=$date)"
    }

}