package com.example.journal;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//represents the journal entry process
public class InputActivity extends AppCompatActivity {
    private String mood = "happy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void submitClicked(View view) {
        addEntry();
    }

    //add entry to the journal db with a given mood, title and context
    public void addEntry() {
        EditText title = findViewById(R.id.editTitle);
        EditText content = findViewById(R.id.editContent);
        JournalEntry entry = new JournalEntry(title.getText().toString(),content.getText().toString(),mood);
        EntryDatabase.getInstance(getApplicationContext()).insert(entry);
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //check which mood is selected by the user
    public void moodClicked(View view) {
        int id = view.getId();
        switch(id) {
            case R.id.sad:
                mood = "sad";
                break;
            case R.id.depressive:
                mood = "depressive";
                break;
            case R.id.funny:
                mood = "funny";
                break;
            case R.id.mad:
                mood = "mad";
                break;
            case R.id.great:
                mood = "great";
                break;
            case R.id.happy:
                mood = "happy";
                break;
        }
        //show the user which mood is selected
        Toast.makeText(getApplicationContext(), mood + " is selected", Toast.LENGTH_SHORT).show();
    }
}
