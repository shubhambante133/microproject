package com.example.votingsystem;

import android.annotation.SuppressLint;
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

public class EditData extends AppCompatActivity {

    EditText Id, Name, Dob, Aadhar, Party, Age;
    Button edit, delete, back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        Id = findViewById(R.id.Id);
        Name = findViewById(R.id.Name);
        Dob = findViewById(R.id.dob);
        Aadhar = findViewById(R.id.Adhar);
        Party = findViewById(R.id.Party);
        Age = findViewById(R.id.Age);

        edit = findViewById(R.id.edit);
        delete = findViewById(R.id.delete);
        back = findViewById(R.id.back);

        Intent i = getIntent();
        Id.setText(i.getStringExtra("id"));
        Name.setText(i.getStringExtra("name"));
        Dob.setText(i.getStringExtra("dob"));
        Aadhar.setText(i.getStringExtra("aadhar"));
        Party.setText(i.getStringExtra("pparty"));
        Age.setText(i.getStringExtra("age"));

        edit.setOnClickListener(v -> updateRecord());
        delete.setOnClickListener(v -> deleteRecord());
        back.setOnClickListener(v -> goToMainActivity());
    }

    private void goToMainActivity() {
        Intent i = new Intent(getApplicationContext(), Manageusers.class);
        startActivity(i);
        finish();
    }

    private void deleteRecord() {
        try {
            String id = Id.getText().toString().trim();
            SQLiteDatabase db = openOrCreateDatabase("VotingSystem", Context.MODE_PRIVATE, null);
            String sql = "DELETE FROM Myvoters WHERE id=?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, id);
            statement.executeUpdateDelete();

            Toast.makeText(this, "Record Deleted Successfully", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), Manageusers.class);
            startActivity(i);
            finish();
        } catch (Exception ex) {
            Toast.makeText(this, "Error Deleting Record", Toast.LENGTH_LONG).show();
        }
    }

    private void updateRecord() {
        try {
            String id = Id.getText().toString().trim();
            String name = Name.getText().toString().trim();
            String dob = Dob.getText().toString().trim();
            String aadhar = Aadhar.getText().toString().trim();
            String pparty = Party.getText().toString().trim();
            String age = Age.getText().toString().trim();

            SQLiteDatabase db = openOrCreateDatabase("VotingSystem", Context.MODE_PRIVATE, null);
            String sql = "UPDATE Myvoters SET name=?, dob=?, aadhar=?, pparty=?, age=? WHERE id=?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, name);
            statement.bindString(2, dob);
            statement.bindString(3, aadhar);
            statement.bindString(4, pparty);
            statement.bindString(5, age);
            statement.bindString(6, id);
            statement.executeUpdateDelete();

            Toast.makeText(this, "Record Updated Successfully", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), Manageusers.class);
            startActivity(i);
            finish();
        } catch (Exception ex) {
            Toast.makeText(this, "Error Updating Record", Toast.LENGTH_LONG).show();
        }
    }
}
