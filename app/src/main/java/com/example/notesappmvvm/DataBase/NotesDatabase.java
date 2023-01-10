package com.example.notesappmvvm.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notesappmvvm.DAO.NotesDao;
import com.example.notesappmvvm.Model.Notes_entity;

@Database(entities =  {Notes_entity.class}, version =  1)
public abstract class NotesDatabase extends RoomDatabase {

    public  abstract NotesDao notesDao();

    public static NotesDatabase INSTANCE;

    public  static  NotesDatabase getDatabaseInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NotesDatabase.class, "Notes_Database")
                    .allowMainThreadQueries().build();
        }
        return  INSTANCE;
    }
}
