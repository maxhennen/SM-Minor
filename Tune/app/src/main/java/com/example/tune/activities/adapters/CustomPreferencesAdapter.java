package com.example.tune.activities.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.tune.R;
import com.example.tune.activities.PostActivity;
import com.example.tune.activities.models.Genre;

public class CustomPreferencesAdapter extends ArrayAdapter {

    private Genre[] genres;
    private Activity context;
    private Genre[] preferences;

    public CustomPreferencesAdapter(Activity context, Genre[] genres, Genre[] preferences) {
        super(context, R.layout.timeline_row, genres);
        this.genres = genres;
        this.context = context;
        this.preferences = preferences;
    }

    public View getView(final int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.preferences_row, null, true);

        TextView genreTxt = (TextView) rowView.findViewById(R.id.preferencesTxt);

        genreTxt.setText(genres[position].toString());

        RadioButton radioButton = rowView.findViewById(R.id.radioBtnPreferences);

        for (Genre genre: preferences) {
            if(genres[position] == genre){
                radioButton.setChecked(true);
            }
        }

        if(position % 2 == 1){
            rowView.setBackgroundColor(ContextCompat.getColor(context, R.color.lightBlue));
        } else {
            rowView.setBackgroundColor(ContextCompat.getColor(context, R.color.darkBlue));
        }

        return rowView;
    }
}
