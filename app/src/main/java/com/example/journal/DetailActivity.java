package com.example.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        JournalEntry retrievedEntry = (JournalEntry) intent.getSerializableExtra("entry");
        System.out.println("titel: " + retrievedEntry.getTitle());
        //set the title of the entry
        TextView titleView = findViewById(R.id.titleJournal);
        titleView.setText(retrievedEntry.getTitle());
        //set the content of the entry
        TextView contentView = findViewById(R.id.contentJournal);
        contentView.setText(retrievedEntry.getContent());
        //set the mood of the entry
        TextView moodView = findViewById(R.id.moodJournal);
        moodView.setText(retrievedEntry.getMood());
        //set the timestamp of the entry
        TextView timeView = findViewById(R.id.timestampJournal);
        timeView.setText(retrievedEntry.getTimestamp());
    }
}
