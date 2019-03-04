package com.example.journal;

import java.io.Serializable;
import java.sql.Timestamp;

public class JournalEntry implements Serializable {
    private int id, mood;
    private String title, content;
    private Timestamp timestamp;

    public JournalEntry(){

    }
}

