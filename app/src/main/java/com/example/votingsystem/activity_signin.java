package com.example.votingsystem;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class activity_signin extends AppCompatActivity {

    EditText etFullName, etEmail, etUsernameSignUp, etPasswordSignUp;
    Button btnSignUpConfirm, btnBackToLogin;
    SQLiteDatabase db; // Use SQLiteDatabase directly

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        // Initialize views
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etUsernameSignUp = findViewById(R.id.etUsernameSignUp);
        etPasswordSignUp = findViewById(R.id.etPasswordSignUp);
        btnSignUpConfirm = findViewById(R.id.btnSignUpConfirm);
        btnBackToLogin = findViewById(R.id.btnBackToLogin);

        // Open or create database
        db = openOrCreateDatabase("VotingSystemDB", MODE_PRIVATE, null);

        // Handle Create Account Button Click
        btnSignUpConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });

        // Handle Back to Login Button Click
        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });
    }

    private void createAccount() {
        String fullName = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String username = etUsernameSignUp.getText().toString().trim();
        String password = etPasswordSignUp.getText().toString().trim();

        // Validate inputs
        if (fullName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert user data into database
        if (insertUser(fullName, email, username, password)) {
            Toast.makeText(this, "Account Created Successfully!", Toast.LENGTH_SHORT).show();
            goToLogin();
        } else {
            Toast.makeText(this, "Account Creation Failed. Username might already exist.", Toast.LENGTH_SHORT).show();
        }
    }

    // Insert new user into database
    private boolean insertUser(String fullName, String email, String username, String password) {
        ContentValues values = new ContentValues();
        values.put("fullname", fullName);
        values.put("email", email);
        values.put("username", username);
        values.put("password", password);

        long result = db.insert("users", null, values);
        return result != -1; // Return true if insert was successful
    }

    private void goToLogin() {
        Intent intent = new Intent(activity_signin.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
