package com.example.tune_kotlin.data

import android.annotation.SuppressLint
import com.example.tune_kotlin.models.Genre
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import org.greenrobot.eventbus.EventBus


class GenreFirebase(): Firebase() {

    @SuppressLint("CheckResult")
    fun getGenres() {
        RxFirebaseDatabase.observeSingleValueEvent(
            reference.child("preferences/" + currentUser?.uid),
            DataSnapshotMapper.listOf<Genre>(Genre::class.java)
        )
            .subscribe { blogPost ->
                blogPost.map { genre ->
                    EventBus.getDefault().post(genre.name)
                }
            }
    }
}