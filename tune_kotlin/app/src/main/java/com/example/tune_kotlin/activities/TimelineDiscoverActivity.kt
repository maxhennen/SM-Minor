package com.example.tune_kotlin.activities

import android.os.Bundle
import android.widget.BaseAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.tune_kotlin.R
import com.example.tune_kotlin.adapters.CustomTimelineAdapter
import com.example.tune_kotlin.data.PostFirebase
import com.example.tune_kotlin.models.Post
import com.example.tune_kotlin.utils.Toolbar
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class TimelineDiscoverActivity : AppCompatActivity() {

    private lateinit var listviewTimeline: ListView
    private lateinit var adapter: BaseAdapter
    private var posts = ArrayList<Post>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline_discover)

        Toolbar(this, "timeline")

        val isTimeline = intent.getBooleanExtra("IS_TIMELINE_ACTIVITY", false)

        listviewTimeline = this.findViewById(R.id.timelineListview)
        val genres: ArrayList<String>

        if (isTimeline) {
            genres = intent.getStringArrayListExtra("TIMELINE_GENRES")
        } else {
            val genre = intent.getStringExtra("DISCOVER_GENRE")
            genres = ArrayList()
            genres.add(genre)
        }
        adapter = CustomTimelineAdapter(this, posts)
        listviewTimeline.adapter = adapter
        PostFirebase(genres).getPosts()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onEvent(post: Post) {
        posts.add(post)
        adapter.notifyDataSetChanged()
    }
}