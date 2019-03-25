package com.example.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.nio.file.ClosedFileSystemException;
//Represents the database that contains records which are journal entries
public class EntryDatabase extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "TABLE_NAME";
    private static EntryDatabase instance;

    private EntryDatabase(Context context) {
        super(context, TABLE_NAME, null, 6);
    }

    //create table of db
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE TABLE_NAME (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, mood TEXT, timestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(createTable);
        db.execSQL("INSERT INTO TABLE_NAME (title, content, mood) VALUES (\"test\", \"best\", \"great\")");
    }

    //delete table if it already exists
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TABLE_NAME");
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

    //insert journal in db
    public void insert(JournalEntry entry) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", entry.getTitle());
        values.put("mood", entry.getMood());
        values.put("content", entry.getContent());
        database.insert("TABLE_NAME",null,values);
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "_id=" + id, null);
    }
}
