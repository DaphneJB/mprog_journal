package com.example.journal;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
//represents the homescreen of the journal app, showing all the journal entries to the user
public class MainActivity extends AppCompatActivity {
    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = EntryDatabase.getInstance(getApplicationContext());
        adapter = new EntryAdapter(getApplicationContext(), db.selectAll());
        ListView view = findViewById(R.id.listview);
        view.setOnItemClickListener(new ClickListener());
        //delete entry when clicked long
        view.setOnItemLongClickListener(new ClickLongListener());
        Parcelable state = view.onSaveInstanceState();
        view.setAdapter(adapter);
        view.onRestoreInstanceState(state);
    }

    public void buttonClicked(View view) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    //clicked on a journal entry
    private class ClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            JournalEntry entry = new JournalEntry(cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("content")),cursor.getString(
                            cursor.getColumnIndex("mood")));
            entry.setTimestamp(cursor.getString(cursor.getColumnIndex("timestamp")));
            intent.putExtra("entry", entry);
            //redirect user for more details about the journal entry
            startActivity(intent);
        }
    }

    //delete when clicked long
    private class ClickLongListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor clickedEntry = (Cursor) parent.getItemAtPosition(position);
            db.delete(clickedEntry.getInt(clickedEntry.getColumnIndex("_id")));
            updateData();
            return true;
        }
    }

    //update screen when entry is added or removed
    private void updateData() {
        adapter.swapCursor(db.selectAll());
    }

    //update screen when entry is added or removed
    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
