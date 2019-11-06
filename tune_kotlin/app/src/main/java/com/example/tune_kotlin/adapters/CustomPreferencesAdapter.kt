package com.example.tune_kotlin.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.tune_kotlin.R
import com.example.tune_kotlin.models.Genre

class CustomPreferencesAdapter(private val context: Activity, private val genres: Array<Genre>, private val preferences: Array<Genre>) :
    BaseAdapter() {
    override fun getItem(position: Int): Any {
        return genres[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()

    }

    override fun getCount(): Int {
        return genres.size
    }

    private val newPreferences: ArrayList<Genre> = ArrayList()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.preferences_row, null, true)

        val genreTxt = rowView.findViewById(R.id.preferencesTxt) as TextView

        genreTxt.text = genres[position].toString()

        val radioButton = rowView.findViewById<CheckBox>(R.id.checkboxPreferences)

        for (genre in preferences) {
            if (genres[position] === genre) {
                radioButton.isChecked = true
                newPreferences.add(genre)
            }
        }

        radioButton.setOnClickListener {
            if(radioButton.isChecked){
                newPreferences.add(Genre.valueOf(genreTxt.text.toString()))
            } else {
                newPreferences.remove(Genre.valueOf(genreTxt.text.toString()))
            }
        }

        if (position % 2 == 1) {
            rowView.setBackgroundColor(ContextCompat.getColor(context, R.color.lightBlue))
        } else {
            rowView.setBackgroundColor(ContextCompat.getColor(context, R.color.darkBlue))
        }

        return rowView
    }

    fun getPreferences(): ArrayList<Genre>{
        return newPreferences
    }

}