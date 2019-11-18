package com.example.tune_kotlin.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.tune_kotlin.R
import com.example.tune_kotlin.adapters.CustomViewPagerAdapter
import com.example.tune_kotlin.models.Post
import com.example.tune_kotlin.utils.Toolbar
import com.google.android.material.tabs.TabLayout


class PostActivity : AppCompatActivity() {

    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        Toolbar(this, "post")

        post = intent.extras!!.getSerializable("POST_INFORMATION") as Post

        val userTxt = findViewById<TextView>(R.id.txtPostUser)
        val userDate = findViewById<TextView>(R.id.txtPostDate)
        val userLocation = findViewById<TextView>(R.id.txtPostLocation)

        userTxt.text = post.email
        userDate.text = post.date
        userLocation.text = post.location

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val adapter = CustomViewPagerAdapter(supportFragmentManager, post)
        adapter.addFragment(AddCommentFragment(post), "add")
        adapter.addFragment(ShowCommentsFragment(post.comments), "show")
        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager)

    }
}