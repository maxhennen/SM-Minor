package com.example.recordingpoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnStart = findViewById(R.id.btnStart);
        final Button btnStop = findViewById(R.id.btnStop);
        btnStop.setEnabled(false);
        final MediaRecorder recorder = new MediaRecorder();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                    0);

        } else {

            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        btnStart.setEnabled(false);
                        btnStop.setEnabled(true);
                        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                        recorder.setOutputFile(getFilePath());
                        recorder.prepare();
                        recorder.start();
                        Toast.makeText(getApplicationContext(), "Start recording!", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        System.out.println(e.getLocalizedMessage());
                    }
                }
            });

            btnStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnStart.setEnabled(true);
                    btnStop.setEnabled(false);
                    recorder.stop();
                    recorder.reset();
                    recorder.release();
                }
            });
        }
    }

    private String getFilePath() {
        String filePath = getApplicationContext().getFilesDir().getAbsolutePath() +
                DateFormat.format("/yyyy-MM-dd_kk-mm-ss", new Date().getTime()) + ".mp3";
        System.out.println("FILE:: " + filePath);
        return filePath;
    }
}
