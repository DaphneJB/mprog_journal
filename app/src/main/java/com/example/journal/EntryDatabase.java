package com.example.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "TABLE_NAME";
    private static EntryDatabase instance;

    private EntryDatabase(Context context) {
        super(context, TABLE_NAME, null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("versies: " + db.getVersion());
        String createTable = "CREATE TABLE TABLE_NAME (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, mood TEXT, timestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP)";
        //System.out.println("versies: " + db.getVersion());
        db.execSQL(createTable);
        db.execSQL("INSERT INTO TABLE_NAME (title, content, mood) VALUES (\"test\", \"best\", \"great\")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TABLE_NAME");
        System.out.print("test");
        onCreate(db);
    }

    public static EntryDatabase getInstance(Context context) {
        if(instance != null) {
            return instance;
        }
        else {
            instance = new EntryDatabase(context);
            return instance;
        }
    }

    public Cursor selectAll() {
        Cursor cursor = getWritableDatabase().rawQuery( "SELECT * FROM TABLE_NAME", null);
        return cursor;
    }

    public void insert(JournalEntry entry) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", entry.getTitle());
        values.put("timestamp", entry.getTimestamp());
        values.put("content", entry.getContent());
        database.insert(TABLE_NAME,null,values);
    }
}
