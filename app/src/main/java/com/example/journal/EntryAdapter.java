package com.example.journal;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.view.View;
import android.support.v4.widget.ResourceCursorAdapter;
//import android.widget.ResourceCursorAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
////Represents an adapter that takes a list of entries and formats it to display
public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, Cursor cursor){
        super(context, R.layout.entry_row, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView titleJournal = view.findViewById(R.id.title);
        //get title of journal entry
        String title = cursor.getString(cursor.getColumnIndex("title"));
        titleJournal.setText(title);

        TextView dateJournal = view.findViewById(R.id.date);
        //get timestamp of journal entry
        String date = cursor.getString(cursor.getColumnIndex("timestamp"));
        dateJournal.setText(date);

        TextView moodJournal = view.findViewById(R.id.mood);
        //get mood of journal entry
        String mood = cursor.getString(cursor.getColumnIndex("mood"));
        moodJournal.setText(mood);
        moodJournal.setTypeface(null, Typeface.ITALIC);
        int drawableId = R.drawable.funny;
        //get drawable id to display the image of the mood from the entry
        switch(mood) {
            case "funny":
                drawableId = R.drawable.funny;
                break;
            case "great":
                drawableId = R.drawable.amazing;
                break;
            case "depressive":
                drawableId = R.drawable.depressive;
                break;
            case "happy":
                drawableId = R.drawable.happy;
                break;
            case "mad":
                drawableId = R.drawable.mad;
                break;
            case "sad":
                drawableId = R.drawable.sad;
                break;
        }
        ImageView moodImage = view.findViewById(R.id.moodImage);
        moodImage.setImageResource(drawableId);
    }
}
