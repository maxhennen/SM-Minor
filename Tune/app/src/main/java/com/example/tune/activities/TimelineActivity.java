package com.example.tune.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tune.R;

import com.example.tune.activities.adapters.CustomTimelineAdapter;
import com.example.tune.activities.models.Comment;
import com.example.tune.activities.models.Genre;
import com.example.tune.activities.models.Post;
import com.example.tune.activities.models.User;

public class TimelineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        Toolbar toolbar = (Toolbar) findViewById(R.id.defaultToolbar);
        TextView txtToolbar = (TextView) findViewById(R.id.textViewToolbar);
        txtToolbar.setText("timeline");
        setSupportActionBar(toolbar);

        ListView listViewTimeline = findViewById(R.id.timelineListview);
        CustomTimelineAdapter adapter = new CustomTimelineAdapter(this, populatePosts());
        listViewTimeline.setAdapter(adapter);
    }

    private Post[] populatePosts(){

        Comment[] comments = {
                new Comment(new User("Max", "max@max.nl", null, "@drawable/profile_picture"), "Abba - mama mia"),
                new Comment(new User("Jan", "jan@jan.nl", null, "@drawable/profile_picture"), "ACDC - Thunder"),
                new Comment(new User("Piet", "piet@piet.nl", null, "@drawable/profile_picture"), "White lies - Unfinished business"),
                new Comment(new User("Harold", "harold@harold.nl", null, "@drawable/profile_picture"), "Blof - zoutelanden"),
                new Comment(new User("Bill", "bill@bill.nl", null, "@drawable/profile_picture"), "Greenday - basket case"),
                new Comment(new User("Henk", "henk@henk.nl", null, "@drawable/profile_picture"), "Mumford and Sons - Little lion man"),
        };

        Post[] posts = {
                new Post("Pinkpop", new User("Max", "max@max.nl", null, "@drawable/profile_picture"), null, Genre.Country, comments),
                new Post("Pukkelpop", new User("Jan", "jan@jan.nl", null, "@drawable/profile_picture"), null, Genre.Rock, comments),
                new Post("Werchter", new User("Piet", "piet@piet.nl", null, "@drawable/profile_picture"), null, Genre.RnB, comments),
                new Post("Nirwana", new User("Harold", "harold@harold.nl", null, "@drawable/profile_picture"), null, Genre.Hiphop, comments),
                new Post("Lowlands", new User("Bill", "bill@bill.nl", null, "@drawable/profile_picture"), null, Genre.Metal, comments),
        };

        return posts;
    }

}
