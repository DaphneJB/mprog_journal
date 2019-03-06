package com.example.journal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private EntryDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = EntryDatabase.getInstance(getApplicationContext());
        EntryAdapter adapter = new EntryAdapter(getApplicationContext(), db.selectAll());
        ListView view = findViewById(R.id.listview);
        view.setOnItemClickListener(new ClickListener());
        view.setOnItemLongClickListener(new ClickLongListener());
        view.setAdapter(adapter);
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
            startActivity(intent);
        }
    }

    private class ClickLongListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor clickedEntry = (Cursor) parent.getItemAtPosition(position);
            db.delete(clickedEntry.getInt(clickedEntry.getColumnIndex("_id")));
            return true;
        }
    }
}
