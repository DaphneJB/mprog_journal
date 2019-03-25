package com.example.journal;

import java.io.Serializable;

//represents a journal entry with a given title, content and mood
public class JournalEntry implements Serializable {
    private String title, content, mood, timestamp;

    public JournalEntry(String journalTitle, String journalContent, String journalMood){
        title = journalTitle;
        mood = journalMood;
        content = journalContent;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getMood() {
        return mood;
    }

    public void setTimestamp(String time) {
        timestamp = time;
    }

    public String getTimestamp() {
        return timestamp;
    }

}

