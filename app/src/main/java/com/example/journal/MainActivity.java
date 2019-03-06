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
        view.setOnItemLongClickListener(new ClickLongListener());
        Parcelable state = view.onSaveInstanceState();
        view.setAdapter(adapter);
        view.onRestoreInstanceState(state);
    }

    public void buttonClicked(View view) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    //geklikt op een dag uit dagboek
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
            startActivity(intent);
        }
    }

    private class ClickLongListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor clickedEntry = (Cursor) parent.getItemAtPosition(position);
            db.delete(clickedEntry.getInt(clickedEntry.getColumnIndex("_id")));
            updateData();
            return true;
        }
    }

    private void updateData() {
        adapter.swapCursor(db.selectAll());
    }

    @Override
    public void onResume() {
        System.out.println("testjes");
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}
