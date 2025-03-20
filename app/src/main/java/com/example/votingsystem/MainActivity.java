package com.example.votingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the splash layout
        setContentView(R.layout.activity_main);

        // Delay and transition to Loginactivity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, Loginactivity.class);
            startActivity(intent);
            finish(); // Finish MainActivity after transition
        }, 2000); // 2-second delay
    }
}
