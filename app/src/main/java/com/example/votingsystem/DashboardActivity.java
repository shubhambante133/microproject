package com.example.votingsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Sabhi Vote Buttons ke liye Click Listener Set Karna
        setVoteButtonClickListener(R.id.btnVote);
        setVoteButtonClickListener(R.id.btnVote2);
        setVoteButtonClickListener(R.id.btnVote3);
        setVoteButtonClickListener(R.id.btnVote4);
        setVoteButtonClickListener(R.id.btnVote5);
        setVoteButtonClickListener(R.id.btnVote6);
    }

    // Ek Common Function jo sabhi Vote Buttons ke liye kaam karega
    private void setVoteButtonClickListener(int buttonId) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Thank you for voting!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
