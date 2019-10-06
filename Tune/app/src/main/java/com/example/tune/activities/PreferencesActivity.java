package com.example.tune.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tune.R;
import com.example.tune.activities.adapters.CustomGenreAdapter;
import com.example.tune.activities.adapters.CustomPreferencesAdapter;
import com.example.tune.activities.models.Genre;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        Toolbar toolbar = (Toolbar) findViewById(R.id.defaultToolbar);
        TextView txtToolbar = (TextView) findViewById(R.id.textViewToolbar);
        txtToolbar.setText("preferences");
        setSupportActionBar(toolbar);

        ListView listViewPreferences = findViewById(R.id.preferencesListview);
        CustomPreferencesAdapter adapter = new CustomPreferencesAdapter(this, Genre.values(), populatePrefrences());
        listViewPreferences.setAdapter(adapter);
    }

    private Genre[] populatePrefrences(){
        Genre[] genres = {
                Genre.Country,
                Genre.Metal,
                Genre.Hiphop
        };
        return genres;
    }
}
