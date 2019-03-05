package com.example.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.support.v4.widget.ResourceCursorAdapter;
//import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, Cursor cursor){
        super(context, R.layout.entry_row, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView titleJournal = view.findViewById(R.id.title);
        //vraagt titel op van dagboekdag
        String title = cursor.getString(cursor.getColumnIndex("title"));
        titleJournal.setText(title);

        TextView dateJournal = view.findViewById(R.id.date);
        //vraagt datum op van dagboekdag
        String date = cursor.getString(cursor.getColumnIndex("timestamp"));
        dateJournal.setText(date);

        TextView moodJournal = view.findViewById(R.id.mood);
        //vraagt mood op van dagboekdag
        String mood = cursor.getString(cursor.getColumnIndex("mood"));
        moodJournal.setText(mood);
    }
}
