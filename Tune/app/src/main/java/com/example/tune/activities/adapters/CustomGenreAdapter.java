package com.example.tune.activities.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.tune.activities.PostActivity;
import com.example.tune.R;

import com.example.tune.activities.models.Genre;

public class CustomGenreAdapter extends ArrayAdapter {

    private Genre[] genres;
    private Activity context;

    public CustomGenreAdapter(Activity context, Genre[] genres) {
        super(context, R.layout.timeline_row, genres);
        this.genres = genres;
        this.context = context;
    }

    public View getView(final int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.genre_row, null, true);

        TextView genreTxt = (TextView) rowView.findViewById(R.id.discoverGenre);

        genreTxt.setText(genres[position].toString());

        Button btnGenre = rowView.findViewById(R.id.discoverGenre);
        btnGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PostActivity.class);
                intent.putExtra("GENRE_INFORMATION", genres[position]);
                context.startActivity(intent);
            }
        });

        if(position % 2 == 1){
            rowView.setBackgroundColor(ContextCompat.getColor(context, R.color.lightBlue));
        } else {
            rowView.setBackgroundColor(ContextCompat.getColor(context, R.color.darkBlue));
        }

        return rowView;
    }
}
