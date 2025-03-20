package com.example.votingsystem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private ListView candidateListView;
    private static final String TAG = "DashboardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        candidateListView = findViewById(R.id.candidateListView);
        display();  // Load candidates dynamically
    }

    // ✅ Load candidates into ListView
    public void display() {
        ArrayList<Manageusers.Voters> candidateList = new ArrayList<>();

        // ✅ Fetching candidate data from database
        SQLiteDatabase db = openOrCreateDatabase("VotingSystem", Context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM Myvoters", null);

        if (c.moveToFirst()) {
            do {
                Manageusers.Voters v = new Manageusers.Voters();
                v.name = c.getString(1);
                v.pparty = c.getString(4);
                v.age = c.getString(5);
                candidateList.add(v);
            } while (c.moveToNext());
        }
        c.close();  // Close the cursor

        // ✅ Add NOTA item dynamically
        Manageusers.Voters notaItem = new Manageusers.Voters();
        notaItem.name = "NOTA";
        notaItem.pparty = "None of the Above";
        notaItem.age = "-";
        candidateList.add(notaItem);

        // ✅ Use CandidateAdapter to populate ListView
        CandidateAdapter adapter = new CandidateAdapter(this, candidateList);
        candidateListView.setAdapter(adapter);
    }
}
