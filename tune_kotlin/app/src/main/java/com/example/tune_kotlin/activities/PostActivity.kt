//package com.example.tune_kotlin.activities
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.TextView
//import androidx.appcompat.widget.Toolbar
//import com.example.tune_kotlin.R
//import com.example.tune_kotlin.models.Post
//
//class PostActivity : AppCompatActivity() {
//
//    private lateinit var post: Post
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_post)
//
//        posaat = intent.extras!!.getSerializable("POST_INFORMATION") as Post
//
//        val toolbar = findViewById(R.id.defaultToolbar) as Toolbar
//        val txtToolbar = findViewById(R.id.textViewToolbar) as TextView
//        txtToolbar.text = "timeline"
//        setSupportActionBar(toolbar)
//
//        val txtUser = findViewById(R.id.txtPostUser)
//        val txtGenre = findViewById(R.id.txtPostGenre)
//        val txtLocation = findViewById(R.id.txtPostLocation)
//
//        txtUser.setText(post!!.getUser().getName())
//        txtGenre.setText(post!!.getGenre().toString())
//        txtLocation.setText(post!!.getLocation())
//
//        val listViewComments = findViewById(R.id.listViewComments)
//        val adapter = CustomCommentsAdapter(this, post!!.getComments())
//        listViewComments.setAdapter(adapter)
//    }
//}
//
