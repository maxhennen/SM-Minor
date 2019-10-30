package com.example.firebasepoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.Date;

public class StorageActivity extends AppCompatActivity {

    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private Button btnSave;
    private final MediaRecorder recorder = new MediaRecorder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFile();
            }
        });
    }

    private void saveFile(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    0);
            saveFile();

        } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    0);
            saveFile();
        } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                    0);
            saveFile();

        } else {

            try {
                String filePath = getFilePath();
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                recorder.setOutputFile(filePath);
                recorder.prepare();
                recorder.start();

                Handler handler = new Handler();
                final File file = new File(filePath);
                handler.postDelayed(new Runnable() {
                    public void run() {
                        recorder.stop();
                        recorder.reset();
                        recorder.release();

                        Uri uri = Uri.fromFile(file);
                        StorageReference audioRef = storageReference.child(currentUser + "/" + file.getName());

                        audioRef.putFile(uri)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Toast.toast(getApplicationContext(), "Success!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.toast(getApplicationContext(), "Failed!");
                                        System.out.println("ERROR:: " + e.getMessage());
                                    }
                                });
                    }
                }, 5000);
            } catch (Exception e){
                System.out.println("ERROR:: " + e.getMessage());
            }
        }
    }

    private void stop(){

    }

    private String getFilePath() {
        String filePath = getApplicationContext().getFilesDir().getAbsolutePath() +
                DateFormat.format("/yyyy-MM-dd_kk-mm-ss", new Date().getTime()) + ".mp3";
        System.out.println("FILE:: " + filePath);
        return filePath;
    }
}
