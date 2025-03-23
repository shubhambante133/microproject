package com.example.votingsystem;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin, btnSignUp;
    SQLiteDatabase db; // Use SQLiteDatabase directly

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);


        // Handle Login Button Click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        // Handle Sign Up Button Click
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUp();
            }
        });
    }

    private void loginUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Admin Login Check
        if (username.equals("admin") && password.equals("admin123")) {
            Toast.makeText(this, "Admin Login Successful!", Toast.LENGTH_SHORT).show();
            goToAdminDashboard(); // Redirect to Admin Dashboard
            return;
        }

        // Check if user exists in the database
        if (checkUser(username, password)) {
            Toast.makeText(this, "User Login Successful!", Toast.LENGTH_SHORT).show();
            goToUserDashboard(); // Redirect to User Dashboard
        } else {
            Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
        }
    }

    // Check if user exists in the database
    private boolean checkUser(String username, String password) {
        db = openOrCreateDatabase("VotingSystem", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE etUsernameSignUp=? AND etPasswordSignUp=?", new String[]{username, password});
        boolean userExists = cursor.moveToFirst();
        cursor.close();
        return userExists;
    }

    private void goToSignUp() {
        Intent intent = new Intent(MainActivity.this, activity_signin.class);
        startActivity(intent);
    }

    private void goToAdminDashboard() {
        Intent intent = new Intent(MainActivity.this, Manageusers.class);
        startActivity(intent);
        finish();
    }

    private void goToUserDashboard() {
        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}
