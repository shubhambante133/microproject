package com.example.votingsystem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Manageusers extends AppCompatActivity {

    EditText name, dob, aadhar, pparty, age;
    Button insert, show;
    ListView lst;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manageusers);

        // Binding UI elements to Java code
        name = findViewById(R.id.name);
        dob = findViewById(R.id.dob);
        aadhar = findViewById(R.id.aadhar);
        pparty = findViewById(R.id.pparty);
        age = findViewById(R.id.age);
        insert = findViewById(R.id.insert);
        show = findViewById(R.id.show);
        lst = findViewById(R.id.listView);

        // Button click listeners
        insert.setOnClickListener(v -> insert());
        show.setOnClickListener(v -> display());
    }

    // ✅ Insert Data
    public void insert() {
        try {
            String n = name.getText().toString().trim();
            String d = dob.getText().toString().trim();
            String aa = aadhar.getText().toString().trim();
            String p = pparty.getText().toString().trim();
            String a = age.getText().toString().trim();

            // Validate input
            if (n.isEmpty() || d.isEmpty() || aa.isEmpty() || p.isEmpty() || a.isEmpty()) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_LONG).show();
                return;
            }

            // Open or create database
            SQLiteDatabase db = openOrCreateDatabase("VotingSystem", Context.MODE_PRIVATE, null);

            // Create table if not exists
            db.execSQL("CREATE TABLE IF NOT EXISTS Myvoters (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, dob TEXT, aadhar TEXT, pparty TEXT, age TEXT)");

            // Insert query
            String sql = "INSERT INTO Myvoters (name, dob, aadhar, pparty, age) VALUES (?, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, n);
            statement.bindString(2, d);
            statement.bindString(3, aa);
            statement.bindString(4, p);
            statement.bindString(5, a);
            statement.execute();

            Toast.makeText(this, "Record Saved Successfully", Toast.LENGTH_LONG).show();
            clearFields();
        } catch (Exception e) {
            Toast.makeText(this, "Error Saving Record: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // ✅ Display Data
    public void display() {
        ArrayList<String> titles = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        lst.setAdapter(arrayAdapter);

        SQLiteDatabase db = openOrCreateDatabase("VotingSystem", Context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM Myvoters", null);

        final ArrayList<Voters> stud = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Voters v = new Voters();
                v.id = c.getString(0);
                v.name = c.getString(1);
                v.dob = c.getString(2);
                v.aadhar = c.getString(3);
                v.pparty = c.getString(4);
                v.age = c.getString(5);
                stud.add(v);
                titles.add(c.getString(0) + " - " + c.getString(1) +
                        "\nDOB: " + c.getString(2) +
                        "\nAadhar: " + c.getString(3) +
                        "\nParty: " + c.getString(4) +
                        "\nAge: " + c.getString(5));
            } while (c.moveToNext());
        }

        lst.setOnItemClickListener((parent, view, position, id) -> {
            Voters vot = stud.get(position);
            if (vot != null) {
                Intent i = new Intent(getApplicationContext(), EditData.class);
                i.putExtra("id", vot.id);
                i.putExtra("name", vot.name);
                i.putExtra("dob", vot.dob);
                i.putExtra("aadhar", vot.aadhar);
                i.putExtra("pparty", vot.pparty);
                i.putExtra("age", vot.age);
                startActivity(i);
            } else {
                Toast.makeText(Manageusers.this, "Error: Invalid data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // ✅ Clear Input Fields
    private void clearFields() {
        name.setText("");
        dob.setText("");
        aadhar.setText("");
        pparty.setText("");
        age.setText("");
        name.requestFocus();
    }

    // ✅ Voters Class
    static class Voters {
        String id, name, dob, aadhar, pparty, age;
    }
}
