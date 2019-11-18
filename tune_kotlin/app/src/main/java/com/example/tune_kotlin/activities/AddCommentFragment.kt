package com.example.tune_kotlin.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.tune_kotlin.R
import com.example.tune_kotlin.data.FirebaseAuth
import com.example.tune_kotlin.data.FirebaseNotification
import com.example.tune_kotlin.data.PostFirebase
import com.example.tune_kotlin.models.Comment
import com.example.tune_kotlin.models.Post
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class AddCommentFragment(post: Post) : Fragment() {

    private val post = post
    private val firebaseAuth = FirebaseAuth()
    private val postFirebase = PostFirebase(null)
    private lateinit var etComment: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        super.onCreateView(inflater, container, savedInstanceState)

        val addCommentView =
            inflater.inflate(R.layout.activity_add_comment_fragment, container, false)

        val btnComment = addCommentView.findViewById<Button>(R.id.btnComment)
        etComment = addCommentView.findViewById(R.id.etComment)

        btnComment.setOnClickListener {
            if(etComment.text.isNotEmpty()) {
                onClick()
            }
        }

        return addCommentView
    }

    fun onClick() {
        val content = etComment.text.toString()
        val currentuser = firebaseAuth.currentUser?.email!!
        val datetime =
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
        val comment = Comment(currentuser, content, datetime)
        if(post.comments == null){
            post.comments = ArrayList()
        }
        post.comments?.add(comment)
        activity?.applicationContext?.let { FirebaseNotification().send(post, comment, it) }

        if(postFirebase.updatePost(post)){
            etComment.text.clear()
        }
    }
}