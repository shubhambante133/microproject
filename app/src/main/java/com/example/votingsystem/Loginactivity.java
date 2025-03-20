package com.example.votingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Loginactivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Loginactivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (username.equals("Tushar") && password.equals("1234")) {
                        Toast.makeText(Loginactivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Loginactivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finish();
                    } if (username.equals("admin") && password.equals("1234")) {
                        Intent intent = new Intent(Loginactivity.this, Manageusers.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}
