package com.example.firebasepoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button registerButton;
    private Button loginButton;
    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if(currentUser == null) {
            registerButton = findViewById(R.id.btn_lregister);
            loginButton = findViewById(R.id.btn_llogin);
            etEmail = findViewById(R.id.et_lemail);
            etPassword = findViewById(R.id.et_lpassword);

            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                    startActivity(intent);
                }
            });

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
                }
            });
        } else {
            Intent intent = new Intent(getApplicationContext(), StorageActivity.class);
            startActivity(intent);
        }
    }

    private void login() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        System.out.println(email + "::" + password);
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), StorageActivity.class);
                            startActivity(intent);
                            Toast.toast(getApplicationContext(), "Succesfully log in!");
                        } else {
                            Toast.toast(getApplicationContext(), "Authentication failed!");
                            System.out.println("ERROR:: " + task.getException().getMessage());
                        }
                    }
                });
    }
}