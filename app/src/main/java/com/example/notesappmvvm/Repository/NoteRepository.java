package com.example.notesappmvvm.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notesappmvvm.DAO.NotesDao;
import com.example.notesappmvvm.DataBase.NotesDatabase;
import com.example.notesappmvvm.Model.Notes_entity;

import java.util.List;

public class NoteRepository {

    public NotesDao notesDao;
    public LiveData<List<Notes_entity>> getAllNotes;

    public LiveData<List<Notes_entity>> highToLow;
    public LiveData<List<Notes_entity>> lowToHigh;

    public NoteRepository(Application application){
        NotesDatabase database = NotesDatabase.getDatabaseInstance(application);
        notesDao = database.notesDao();
        getAllNotes = notesDao.getAllNotes();

        highToLow = notesDao.highToLow();
        lowToHigh = notesDao.lowToHigh();
    }

    public void  insertNotes(Notes_entity notes){
        notesDao.insetNotes(notes);
    }

    public void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }

    public void updateNotes(Notes_entity notes){
        notesDao.updateNote(notes);
    }


}
