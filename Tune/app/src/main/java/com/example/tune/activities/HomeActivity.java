package com.example.tune.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.tune.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.defaultToolbar);
        setSupportActionBar(toolbar);

        ImageButton btnTimeline = (ImageButton) findViewById(R.id.homeTimeline);
        btnTimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toTimeline = new Intent(HomeActivity.this, TimelineActivity.class);
                startActivity(toTimeline);
            }
        });

        ImageButton btnRecord = (ImageButton) findViewById(R.id.homeRecord);
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRecord = new Intent(HomeActivity.this, RecordActivity.class);
                startActivity(toRecord);
            }
        });

        ImageButton btnDiscover = (ImageButton) findViewById(R.id.homeDiscover);
        btnDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRecord = new Intent(HomeActivity.this, DiscoverActivity.class);
                startActivity(toRecord);
            }
        });

        ImageButton btnPreferences = (ImageButton) findViewById(R.id.homePreferences);
        btnPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRecord = new Intent(HomeActivity.this, PreferencesActivity.class);
                startActivity(toRecord);
            }
        });

    }
}
