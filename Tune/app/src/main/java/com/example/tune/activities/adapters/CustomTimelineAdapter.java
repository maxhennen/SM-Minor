package com.example.tune.activities.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.tune.activities.PostActivity;
import com.example.tune.R;

import com.example.tune.activities.models.Post;

public class CustomTimelineAdapter extends ArrayAdapter {

    private Post[] posts;
    private Activity context;

    public CustomTimelineAdapter(Activity context, Post[] posts) {
        super(context, R.layout.timeline_row, posts);
        this.posts = posts;
        this.context = context;
    }

    public View getView(final int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.timeline_row, null, true);

        TextView userTxt = (TextView) rowView.findViewById(R.id.timelineUser);
        TextView genreTxt = (TextView) rowView.findViewById(R.id.timelineGenre);
        TextView locationTxt = (TextView) rowView.findViewById(R.id.timelineLocation);

        userTxt.setText(posts[position].getUser().getName());
        genreTxt.setText(posts[position].getGenre().toString());
        locationTxt.setText(posts[position].getLocation());

        LinearLayout linearLayout = rowView.findViewById(R.id.linearLayoutOnClick);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PostActivity.class);
                intent.putExtra("POST_INFORMATION", posts[position]);
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
