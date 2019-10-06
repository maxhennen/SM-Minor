package com.example.tune.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tune.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.defaultToolbar);
        TextView txtToolbar = (TextView) findViewById(R.id.textViewToolbar);
        txtToolbar.setText("profile");
        setSupportActionBar(toolbar);

    }
}
