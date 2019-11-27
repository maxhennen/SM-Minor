package com.example.tune_kotlin.activities

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.tune_kotlin.R
import com.example.tune_kotlin.adapters.CustomViewPagerAdapter
import com.example.tune_kotlin.data.StorageFirebase
import com.example.tune_kotlin.models.Post
import com.example.tune_kotlin.utils.Toolbar
import com.google.android.material.tabs.TabLayout
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class PostActivity : AppCompatActivity() {

    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        Toolbar(this, "post")

        post = intent.extras!!.getSerializable("POST_INFORMATION") as Post
        StorageFirebase().getPostFile(post)

        val userTxt = findViewById<TextView>(R.id.txtPostUser)
        val userDate = findViewById<TextView>(R.id.txtPostDate)
        val userLocation = findViewById<TextView>(R.id.txtPostLocation)

        val playBtn = findViewById<ImageButton>(R.id.btnPlayPost)

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
        val mediaPlayer = MediaPlayer()
        mediaPlayer.reset()

        playBtn.setOnClickListener {
            try {
                if(post.bytes?.isNotEmpty()!!) {
                    val tempMp3 = File.createTempFile("kurchina", "mp3", cacheDir)
                    tempMp3.deleteOnExit()
                    val fos = FileOutputStream(tempMp3)
                    fos.write(post.bytes)
                    fos.close()


                    val fis = FileInputStream(tempMp3)
                    mediaPlayer.setDataSource(fis.fd)

                    mediaPlayer.prepare()
                    mediaPlayer.start()
                }
            } catch (ex: Exception) {
                println(ex.message)
            }
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
    fun onEvent(bytes: ByteArray) {
        post.bytes = bytes
    }
}