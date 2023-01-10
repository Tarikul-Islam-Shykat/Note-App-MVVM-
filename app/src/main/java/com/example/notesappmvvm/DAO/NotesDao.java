package com.example.notesappmvvm.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notesappmvvm.Model.Notes_entity;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT *  FROM notes_database")
    LiveData<List<Notes_entity>> getAllNotes();

    @Query("SELECT *  FROM notes_database ORDER BY notes_priority DESC")
    LiveData<List<Notes_entity>> highToLow();

    @Query("SELECT *  FROM notes_database ORDER BY notes_priority ASC")
    LiveData<List<Notes_entity>> lowToHigh();

    @Insert
    void  insetNotes(Notes_entity... notes);

    @Query("DELETE FROM Notes_Database WHERE id=:id")
    void deleteNotes(int id);

    @Update
    void  updateNote(Notes_entity notes);

}
