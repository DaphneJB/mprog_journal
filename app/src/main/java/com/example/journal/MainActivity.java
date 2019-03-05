package com.example.journal;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        EntryAdapter adapter = new EntryAdapter(getApplicationContext(), db.selectAll());
        ListView view = findViewById(R.id.listview);
        view.setOnItemClickListener(new ClickListener());
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
}
