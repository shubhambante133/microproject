package com.example.votingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
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
        db = openOrCreateDatabase("VotingSystem", MODE_PRIVATE, null);

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

        // Call insert with parameters
        insert(fullName, email, username, password);
    }


    // Insert new user into database
    public void insert(String fullName, String email, String username, String password) {
        try {
            // Validate input
            if (fullName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_LONG).show();
                return;
            }

            // Open or create database
            SQLiteDatabase db = openOrCreateDatabase("VotingSystem", Context.MODE_PRIVATE, null);

            // Create table if not exists
            db.execSQL("CREATE TABLE IF NOT EXISTS Users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "etFullName TEXT, etEmail TEXT, etUsernameSignUp TEXT, etPasswordSignUp TEXT)");

            // Insert query
            String sql = "INSERT INTO Users (etFullName, etEmail, etUsernameSignUp, etPasswordSignUp) VALUES (?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, fullName);
            statement.bindString(2, email);
            statement.bindString(3, username);
            statement.bindString(4, password);
            statement.execute();

            Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_LONG).show();
            clearFields();
        } catch (Exception e) {
            Toast.makeText(this, "Account Creation Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void goToLogin() {
        Intent intent = new Intent(activity_signin.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void clearFields() {
        etFullName.setText("");
        etEmail.setText("");
        etUsernameSignUp.setText("");
        etPasswordSignUp.setText("");
        etUsernameSignUp.requestFocus();
        etPasswordSignUp.requestFocus();
    }
    // You can either remove this or define a valid class
    static class Users {
        String name;
        String email;
        String username;
        String password;
    }

}
