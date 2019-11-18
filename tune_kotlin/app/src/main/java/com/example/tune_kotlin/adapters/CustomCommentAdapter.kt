package com.example.tune_kotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.tune_kotlin.R
import com.example.tune_kotlin.models.Comment

class CustomCommentAdapter(private val context: Context, private val comments: ArrayList<Comment>?) : BaseAdapter() {

    override fun getItem(position: Int): Comment? {
        return comments?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return comments?.get(position)?.id!!
    }

    override fun getCount(): Int {
        return comments?.size!!
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.comment_row, null, true)

        if(comments?.isNotEmpty()!!) {
            val userTxt = rowView.findViewById(R.id.commentUser) as TextView
            val genreTxt = rowView.findViewById(R.id.commentContent) as TextView

            userTxt.text = comments[position].user
            genreTxt.text = comments[position].content

            if (position % 2 == 1) {
                rowView.setBackgroundColor(ContextCompat.getColor(context, R.color.lightBlue))
            } else {
                rowView.setBackgroundColor(ContextCompat.getColor(context, R.color.darkBlue))
            }
        } else {
            val userTxt = rowView.findViewById(R.id.timelineUser) as TextView
            userTxt.text = "No comments available! Check your preferences."

        }
        return rowView
    }
}