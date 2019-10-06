package com.example.tune.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tune.R;

import com.example.tune.activities.adapters.CustomCommentsAdapter;

import com.example.tune.activities.models.Post;

public class PostActivity extends AppCompatActivity {

    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        post = (Post)getIntent().getExtras().getSerializable("POST_INFORMATION");

        Toolbar toolbar = (Toolbar) findViewById(R.id.defaultToolbar);
        TextView txtToolbar = (TextView) findViewById(R.id.textViewToolbar);
        txtToolbar.setText("timeline");
        setSupportActionBar(toolbar);

        TextView txtUser = findViewById(R.id.txtPostUser);
        TextView txtGenre = findViewById(R.id.txtPostGenre);
        TextView txtLocation = findViewById(R.id.txtPostLocation);

        txtUser.setText(post.getUser().getName());
        txtGenre.setText(post.getGenre().toString());
        txtLocation.setText(post.getLocation());

        ListView listViewComments = findViewById(R.id.listViewComments);
        CustomCommentsAdapter adapter = new CustomCommentsAdapter(this, post.getComments());
        listViewComments.setAdapter(adapter);
    }
}
