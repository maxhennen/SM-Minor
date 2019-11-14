package com.example.tune_kotlin.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.tune_kotlin.R
import com.example.tune_kotlin.adapters.CustomCommentAdapter
import com.example.tune_kotlin.models.Comment

class ShowCommentsFragment(comments: ArrayList<Comment>?) : Fragment() {

    private val comments = comments

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        super.onCreateView(inflater, container, savedInstanceState)
        val showCommentsView = inflater.inflate(R.layout.activity_show_comments_fragment, container, false)

        val listviewComments = showCommentsView.findViewById<ListView>(R.id.listViewComments)
        val adapter = CustomCommentAdapter(showCommentsView.context, comments)
        listviewComments.adapter = adapter

        return showCommentsView
    }
}
