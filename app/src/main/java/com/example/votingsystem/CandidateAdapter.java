package com.example.votingsystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class CandidateAdapter extends ArrayAdapter<Manageusers.Voters> {

    private Context context;
    private ArrayList<Manageusers.Voters> candidateList;

    public CandidateAdapter(Context context, ArrayList<Manageusers.Voters> candidateList) {
        super(context, R.layout.candidate_item, candidateList);
        this.context = context;
        this.candidateList = candidateList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.candidate_item, parent, false);
        }

        // Get current candidate
        Manageusers.Voters currentCandidate = candidateList.get(position);

        // ✅ Find and set views
        TextView candidateInfoTextView = itemView.findViewById(R.id.candidateInfoTextView);
        Button voteButton = itemView.findViewById(R.id.voteButton);
        ImageView candidateImage = itemView.findViewById(R.id.candidateImage);

        // ✅ Check if it's NOTA
        if (currentCandidate.name.equals("NOTA")) {
            candidateInfoTextView.setText("Vote for (NOTA)");
            candidateImage.setImageResource(R.drawable.wrong);  // Add NOTA icon in drawable folder
        } else {
            candidateInfoTextView.setText(currentCandidate.name + " - " + currentCandidate.pparty + "\nAge: " + currentCandidate.age);
            candidateImage.setImageResource(R.drawable.candidate1);  // Add Candidate icon in drawable
        }

        // ✅ Handle Vote Button Click
        voteButton.setOnClickListener(v -> {
            // ✅ Show a confirmation message
            String voteMessage = currentCandidate.name.equals("NOTA") ?
                    "You voted for NOTA!" : "You voted for " + currentCandidate.name;
            Toast.makeText(context, voteMessage, Toast.LENGTH_LONG).show();

            // ✅ Return to MainActivity after voting
            Intent intent = new Intent(context, Loginactivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        });

        return itemView;
    }
}
