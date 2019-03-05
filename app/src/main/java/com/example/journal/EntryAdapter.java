package com.example.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;

public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, Cursor cursor){
        super(context, R.layout.entry_row, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
