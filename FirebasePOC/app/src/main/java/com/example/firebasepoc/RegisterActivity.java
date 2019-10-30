package com.example.firebasepoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirm;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        btnRegister = findViewById(R.id.btn_register);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etConfirm = findViewById(R.id.et_cpassword);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirm = etConfirm.getText().toString();

        if(!email.equals("") && !password.equals("")){
            if(validateEmail(email)){
                if(validatePassword(password)){
                    if(password.equals(confirm)){
                        saveToFirebase(email, password);
                    } else {
                        toast("Passwordfields don't match!");
                    }
                } else {
                    toast("Password doesn't match requirements!");
                }
            } else {
                toast("Invalid email!");
            }
        } else {
            toast("Not all fields are filled in!");
        }

    }

    private void toast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private boolean validateEmail(String email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validatePassword(String password){
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private void saveToFirebase(String email, String password){
        System.out.println(email + "::" + password);
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            toast("Registration succesfull!");
                        } else {
                            System.out.println("ERORR:: " +task.getException().getMessage());
                            toast("Something went wrong");
                        }
                    }
                });
    }
}
