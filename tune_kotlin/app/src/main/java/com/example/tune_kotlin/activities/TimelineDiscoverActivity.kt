package com.example.tune_kotlin.activities

import android.os.Bundle
import android.widget.BaseAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.tune_kotlin.R
import com.example.tune_kotlin.adapters.CustomTimelineAdapter
import com.example.tune_kotlin.data.Firebase
import com.example.tune_kotlin.models.Post

class TimelineDiscoverActivity : AppCompatActivity() {

    private lateinit var listviewTimeline: ListView
    private lateinit var adapter: BaseAdapter
    private val firebase = Firebase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline_discover)

        val isTimeline = intent.getBooleanExtra("IS_TIMELINE_ACTIVITY", false)

        listviewTimeline = findViewById(R.id.timelineListview)
        val genres: ArrayList<String>

        if (isTimeline) {
            genres = intent.getStringArrayListExtra("TIMELINE_GENRES")
        } else {
            val genre = intent.getStringExtra("DISCOVER_GENRE")
            genres = ArrayList()
            genres.add(genre)
        }

        val posts = ArrayList<Post>()
        genres.map { genre ->
            posts.addAll(firebase.getPosts(genre))
        }
        adapter = CustomTimelineAdapter(this, posts)
    }
}
