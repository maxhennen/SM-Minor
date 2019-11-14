package com.example.tune_kotlin.models

import com.google.firebase.auth.FirebaseUser
import java.io.Serializable

class Comment() : Serializable {
    var id: Long? = null
    var user: String? = null
    var content: String? = null
    var date: String? = null

    constructor(user: String?, content: String?, date: String?): this(){
        this.user = user
        this.content = content
        this.date = date
    }
}