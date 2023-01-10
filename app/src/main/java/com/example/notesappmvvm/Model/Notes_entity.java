package com.example.notesappmvvm.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_Database")
public class Notes_entity {

    @PrimaryKey(autoGenerate = true)
    public  int id;

    @ColumnInfo(name = "notes_title")
    public String notesTitle;

    @ColumnInfo(name = "notes_subtitle")
    public String notesSubtitle;

    @ColumnInfo(name = "notes_date")
    public String notesData;

    @ColumnInfo(name = "notes")
    public String notes;

    @ColumnInfo(name = "notes_priority")
    public String notesPriority;



    
}

/*
* A Room entity includes fields for each column in the corresponding table in the database, including one or more columns that comprise the primary key
* */
