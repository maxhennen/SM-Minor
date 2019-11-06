package com.example.tune_kotlin.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import android.os.Bundle
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

import com.example.tune_kotlin.R
import com.example.tune_kotlin.adapters.CustomTimelineAdapter
import com.example.tune_kotlin.models.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.example.tune_kotlin.models.Genre
import durdinapps.rxfirebase2.RxFirebaseDatabase
import android.annotation.SuppressLint
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseQuery


class TimelineActivity : AppCompatActivity() {

    private lateinit var listviewTimeline: ListView
    private lateinit var adapter: BaseAdapter
    private val posts: ArrayList<Post> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)

        val toolbar = findViewById<Toolbar>(R.id.defaultToolbar)
        val txtToolbar = findViewById<TextView>(R.id.textViewToolbar)
        txtToolbar.text = "timeline"
        setSupportActionBar(toolbar)

        listviewTimeline = findViewById(R.id.timelineListview)
        populatePosts()
        adapter = CustomTimelineAdapter(this, posts)
        listviewTimeline.adapter = adapter
    }

    @SuppressLint("CheckResult")
    private fun populatePosts() {

        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.currentUser
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val reference: DatabaseReference = database.reference

        val genres: ArrayList<Genre> = ArrayList()

        RxFirebaseDatabase.observeSingleValueEvent(
            reference.child("preferences/" + currentUser?.uid),
            DataSnapshotMapper.listOf(Genre::class.java)
        ).subscribe { datasnapshot ->
            datasnapshot.map { genre ->
                genres.add(genre)
            }
        }

//        genres.map { genre ->
//            RxFirebaseDatabase.observeSingleValueEvent(reference.child(genre))
//        }
    }
}

