package com.example.tune.activities.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.tune.R;

import com.example.tune.activities.models.Comment;

public class CustomCommentsAdapter extends ArrayAdapter {

    private Comment[] comments;
    private Activity context;

    public CustomCommentsAdapter(Activity context, Comment[] comments) {
        super(context, R.layout.timeline_row, comments);
        this.comments = comments;
        this.context = context;
    }

    public View getView(final int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.comment_row, null, true);

        TextView userTxt = (TextView) rowView.findViewById(R.id.commentUser);
        TextView contentTxt = (TextView) rowView.findViewById(R.id.commentContent);

        userTxt.setText(comments[position].getUser().getName());
        contentTxt.setText(comments[position].getContent());


        if(position % 2 == 1){
            rowView.setBackgroundColor(ContextCompat.getColor(context, R.color.lightBlue));
        } else {
            rowView.setBackgroundColor(ContextCompat.getColor(context, R.color.darkBlue));
        }

        return rowView;
    }
}
