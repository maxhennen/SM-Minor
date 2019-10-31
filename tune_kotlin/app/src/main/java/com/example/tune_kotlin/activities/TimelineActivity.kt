//package com.example.tune.activities
//
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.Toolbar
//
//import android.os.Bundle
//import android.widget.ListView
//import android.widget.TextView
//
//import com.example.tune_kotlin.R
//import com.example.tune_kotlin.adapters.CustomTimelineAdapter
//
//class TimelineActivity : AppCompatActivity() {
//
//    private lateinit var listviewTimeline: ListView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_timeline)
//
//        val toolbar = findViewById(R.id.defaultToolbar) as Toolbar
//        val txtToolbar = findViewById(R.id.textViewToolbar) as TextView
//        txtToolbar.text = "timeline"
//        setSupportActionBar(toolbar)
//
//        listviewTimeline = findViewById(R.id.timelineListview)
//        val adapter = CustomTimelineAdapter(this, populatePosts())
//        listViewTimeline.setAdapter(adapter)
//    }
//
////    private fun populatePosts(): Array<Post> {
////
////        val genrePreferences: Genre[] {
////
////        }
////
////        val comments = arrayOf<Comment>(
////            Comment(
////                User("Max", "max@max.nl", null, "@drawable/profile_picture"),
////                "Abba - mama mia"
////            ),
////            Comment(User("Jan", "jan@jan.nl", null, "@drawable/profile_picture"), "ACDC - Thunder"),
////            Comment(
////                User("Piet", "piet@piet.nl", null, "@drawable/profile_picture"),
////                "White lies - Unfinished business"
////            ),
////            Comment(
////                User("Harold", "harold@harold.nl", null, "@drawable/profile_picture"),
////                "Blof - zoutelanden"
////            ),
////            Comment(
////                User("Bill", "bill@bill.nl", null, "@drawable/profile_picture"),
////                "Greenday - basket case"
////            ),
////            Comment(
////                User("Henk", "henk@henk.nl", null, "@drawable/profile_picture"),
////                "Mumford and Sons - Little lion man"
////            )
////        )
////
////        return arrayOf<Post>(
////            Post(
////                "Pinkpop",
////                User("Max", "max@max.nl", null, "@drawable/profile_picture"),
////                null,
////                Genre.Country,
////                comments
////            ),
////            Post(
////                "Pukkelpop",
////                User("Jan", "jan@jan.nl", null, "@drawable/profile_picture"),
////                null,
////                Genre.Rock,
////                comments
////            ),
////            Post(
////                "Werchter",
////                User("Piet", "piet@piet.nl", null, "@drawable/profile_picture"),
////                null,
////                Genre.RnB,
////                comments
////            ),
////            Post(
////                "Nirwana",
////                User("Harold", "harold@harold.nl", null, "@drawable/profile_picture"),
////                null,
////                Genre.Hiphop,
////                comments
////            ),
////            Post(
////                "Lowlands",
////                User("Bill", "bill@bill.nl", null, "@drawable/profile_picture"),
////                null,
////                Genre.Metal,
////                comments
////            )
////        )
////    }
//
//}
