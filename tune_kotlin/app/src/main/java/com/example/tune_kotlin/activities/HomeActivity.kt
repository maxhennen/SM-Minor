package com.example.tune_kotlin.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.tune_kotlin.R
import com.example.tune_kotlin.data.FirebaseNotification
import com.example.tune_kotlin.data.GenreFirebase
import com.example.tune_kotlin.models.Genre
import com.example.tune_kotlin.utils.Toolbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class HomeActivity : AppCompatActivity() {

    private val genres = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Toolbar(this, "home")

        GenreFirebase().getGenres()

        val btnTimeline = findViewById<ImageButton>(R.id.homeTimeline)
        btnTimeline.setOnClickListener {
            if(genres.isNotEmpty()) {
                val intent = Intent(this, TimelineDiscoverActivity::class.java)
                intent.putExtra("IS_TIMELINE_ACTIVITY", true)
                intent.putStringArrayListExtra("TIMELINE_GENRES", genres)
                startActivity(intent)
            }
        }

        val btnRecord = findViewById<ImageButton>(R.id.homeRecord)
        btnRecord.setOnClickListener {
            val toRecord = Intent(this@HomeActivity, RecordActivity::class.java)
            startActivity(toRecord)
        }

        val btnDiscover = findViewById<ImageButton>(R.id.homeDiscover)
        btnDiscover.setOnClickListener {
            val toDiscover = Intent(this@HomeActivity, DiscoverActivity::class.java)
            startActivity(toDiscover)
        }

        val btnPreferences = findViewById<ImageButton>(R.id.homePreferences)
        btnPreferences.setOnClickListener {
            val toPreferences = Intent(this@HomeActivity, PreferencesActivity::class.java)
            toPreferences.putExtra("PREFERENCES_GENRES", genres)
            startActivity(toPreferences)
        }

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
    fun onEvent(genre: String){
        FirebaseNotification().init(genre)
        genres.add(genre)
    }
}
