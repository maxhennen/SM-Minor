package com.example.tune_kotlin.data

import android.annotation.SuppressLint
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import org.greenrobot.eventbus.EventBus


class GenreFirebase(): Firebase() {

    val referenceGenre = reference.child("preferences/" + currentUser?.uid)

    @SuppressLint("CheckResult")
    fun getGenres() {

        referenceGenre.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.map { genre ->
                    EventBus.getDefault().post(genre.value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println(error.message)
            }
        })
    }
}