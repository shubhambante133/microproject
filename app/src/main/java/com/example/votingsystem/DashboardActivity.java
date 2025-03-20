package com.example.votingsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private LinearLayout candidateListLayout;
    private List<Candidate> candidates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard); // Ensure this matches your XML file

        candidateListLayout = findViewById(R.id.candidateContainer);

        // Check if candidateListLayout is null (avoid NullPointerException)
        if (candidateListLayout == null) {
            throw new NullPointerException("Error: candidateContainer is null. Check activity_dashboard.xml.");
        }

        // Initialize candidate data
        candidates = new ArrayList<>();
        candidates.add(new Candidate("Candidate 1", "Party A", 45));
        candidates.add(new Candidate("Candidate 2", "Party B", 38));
        candidates.add(new Candidate("Candidate 3", "Party C", 52));
        candidates.add(new Candidate("Candidate 4", "Party D", 41));
        candidates.add(new Candidate("Candidate 5", "Party E", 60));
        candidates.add(new Candidate("Candidate 6", "Party F", 35));

        // Add candidates to the layout
        for (Candidate candidate : candidates) {
            addCandidateView(candidate);
        }

        // Add NOTA (None of the Above) option
        addNotaView();
    }

    private void addCandidateView(Candidate candidate) {
        View candidateView = LayoutInflater.from(this).inflate(R.layout.candidate_item, candidateListLayout, false);

        TextView nameTextView = candidateView.findViewById(R.id.candidate_name);
        TextView partyTextView = candidateView.findViewById(R.id.candidate_party);
        TextView ageTextView = candidateView.findViewById(R.id.candidate_age);

        if (nameTextView != null && partyTextView != null && ageTextView != null) {
            nameTextView.setText(candidate.getName());
            partyTextView.setText(candidate.getParty());
            ageTextView.setText(String.valueOf(candidate.getAge()));
        } else {
            throw new NullPointerException("Error: One or more TextViews are null in candidate_item.xml.");
        }

        candidateView.findViewById(R.id.vote_button).setOnClickListener(v -> {
            Toast.makeText(DashboardActivity.this, "Voted for " + candidate.getName(), Toast.LENGTH_SHORT).show();
        });

        candidateListLayout.addView(candidateView);
    }

    private void addNotaView() {
        View notaView = LayoutInflater.from(this).inflate(R.layout.nota_item, candidateListLayout, false);

        if (notaView != null) {
            notaView.findViewById(R.id.nota_button).setOnClickListener(v -> {
                Toast.makeText(DashboardActivity.this, "Voted for NOTA", Toast.LENGTH_SHORT).show();
            });

            candidateListLayout.addView(notaView);
        } else {
            throw new NullPointerException("Error: NOTA layout is null.");
        }
    }

    // Candidate data model
    private static class Candidate {
        private final String name;
        private final String party;
        private final int age;

        public Candidate(String name, String party, int age) {
            this.name = name;
            this.party = party;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public String getParty() {
            return party;
        }

        public int getAge() {
            return age;
        }
    }
}
