package com.example.journal;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void submitClicked(View view) {
        addEntry();
    }

    public void addEntry() {
        EditText title = findViewById(R.id.editTitle);
        EditText content = findViewById(R.id.editContent);
        JournalEntry entry = new JournalEntry(title.getText().toString(),content.getText().toString(),"great");
        EntryDatabase.getInstance(getApplicationContext()).insert(entry);
    }

    public void moodClicked(View view) {
        //view.setAlpha(1);
        view.setBackgroundColor(Color.GRAY);
    }


}
