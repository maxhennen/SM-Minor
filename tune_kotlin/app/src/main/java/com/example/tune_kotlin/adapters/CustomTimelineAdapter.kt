package com.example.tune_kotlin.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.tune_kotlin.R
import com.example.tune_kotlin.activities.PostActivity
//import com.example.tune_kotlin.activities.PostActivity
import com.example.tune_kotlin.models.Post

class CustomTimelineAdapter(private val context: Context, private val posts: List<Post>) :BaseAdapter() {
    override fun getItem(position: Int): Any {
        return posts[position]
    }

    override fun getItemId(position: Int): Long {
        return posts[position].id ?:0

    }

    override fun getCount(): Int {
        return posts.size
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.timeline_row, null, true)
        if(posts.size != 0) {
            val userTxt = rowView.findViewById(R.id.timelineUser) as TextView
            val genreTxt = rowView.findViewById(R.id.timelineGenre) as TextView
            val locationTxt = rowView.findViewById(R.id.timelineLocation) as TextView

            userTxt.text = posts[position].email
            genreTxt.text = posts[position].genre?.name
            locationTxt.text = posts[position].location


            if (position % 2 == 1) {
                rowView.setBackgroundColor(ContextCompat.getColor(context, R.color.lightBlue))
            } else {
                rowView.setBackgroundColor(ContextCompat.getColor(context, R.color.darkBlue))
            }
        } else {
            val userTxt = rowView.findViewById(R.id.timelineUser) as TextView
            userTxt.text = "No posts available! Check your preferences."

        }

        rowView.setOnClickListener{
            val intent = Intent(context, PostActivity::class.java)
            intent.putExtra("POST_INFORMATION", posts[position])
            context.startActivity(intent)
        }

        return rowView
    }

}
