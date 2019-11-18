package com.example.tune_kotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.example.tune_kotlin.R
import com.example.tune_kotlin.models.Genre

class CustomPreferencesAdapter(private val context: Context, private val preferences: ArrayList<String>) : BaseAdapter() {

    private val genres = Genre.values()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        val inflater: LayoutInflater
                = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if(convertView == null){
            view = inflater.inflate(R.layout.preferences_row, parent, false)
            viewHolder = ViewHolder()
            viewHolder.genre = view.findViewById(R.id.preferencesTxt)
            viewHolder.checkbox = view.findViewById(R.id.checkboxPreferences)

            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        val genreTxt = viewHolder.genre
        val checkBox = viewHolder.checkbox

        preferences.map { genre ->
            if(genre == genres[position].name){
                checkBox.isChecked = true
            }
        }

        checkBox.setOnClickListener {
            checkBox.isChecked = !checkBox.isChecked
        }
        return view!!
    }

    override fun getItem(position: Int): Any {
        return genres[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return genres.size
    }

    private class ViewHolder {
        lateinit var genre: TextView
        lateinit var checkbox: CheckBox
    }
}