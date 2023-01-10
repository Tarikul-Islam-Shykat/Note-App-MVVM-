package com.example.notesappmvvm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notesappmvvm.Model.Notes_entity;
import com.example.notesappmvvm.Repository.NoteRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NoteRepository repository;
    public LiveData<List<Notes_entity>> getAllNotes;

    public LiveData<List<Notes_entity>> highToLow;
    public LiveData<List<Notes_entity>> lowToHigh;


    public NotesViewModel(Application application) {
        super(application);
        repository = new NoteRepository(application);
        getAllNotes = repository.getAllNotes;

        highToLow = repository.highToLow;
        lowToHigh = repository.lowToHigh;
    }

    public void  insertNote(Notes_entity notes){
        repository.insertNotes(notes);
    }

    public void deleteNote(int id){
        repository.deleteNotes(id);
    }

    public void updateNote(Notes_entity notes){
        repository.updateNotes(notes);
    }



}
