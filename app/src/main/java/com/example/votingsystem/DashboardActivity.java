package com.example.votingsystem;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    private LinearLayout candidateContainer; // Reference to the LinearLayout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize LinearLayout from XML
        candidateContainer = findViewById(R.id.candidateContainer);

        // Ensure that the LinearLayout is not null
        if (candidateContainer == null) {
            throw new NullPointerException("Error: candidateContainer is null. Check your XML layout ID.");
        }

        // Add some candidate views dynamically for testing
        addCandidateView("Candidate 1");
        addCandidateView("Candidate 2");
    }

    private void addCandidateView(String candidateName) {
        TextView textView = new TextView(this);
        textView.setText(candidateName);
        textView.setTextSize(18);
        textView.setPadding(10, 10, 10, 10);

        // Add the TextView to the LinearLayout
        if (candidateContainer != null) {
            candidateContainer.addView(textView);
        } else {
            throw new NullPointerException("candidateContainer is null inside addCandidateView");
        }
    }
}
