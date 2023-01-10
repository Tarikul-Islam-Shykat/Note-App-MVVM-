package com.example.notesappmvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.notesappmvvm.Activity.InsertNotesActivity;
import com.example.notesappmvvm.Adapter.NotesAdapter;
import com.example.notesappmvvm.Model.Notes_entity;
import com.example.notesappmvvm.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;



import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    NotesViewModel notesViewModel;
    RecyclerView recyclerView;
    NotesAdapter notesAdapter;

    TextView noFilter, hightToLow, lowToHigh;

    List<Notes_entity> fiterNotesAllList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        floatingActionButton = findViewById(R.id.add_notes_btn);
        recyclerView = findViewById(R.id.recView_am);

        noFilter = findViewById(R.id.am_txt_noFilter);
        hightToLow = findViewById(R.id.am_txt_HighToLow);
        lowToHigh = findViewById(R.id.am_txt_LowToHigh);

        noFilter.setBackgroundResource(R.drawable.filter_selected_shape);

        noFilter.setOnClickListener(v -> {
            filter(0);
            loadData(0);
        });

        hightToLow.setOnClickListener(v -> {
            filter(1);
            loadData(1);
        });

        lowToHigh.setOnClickListener(v->{
            filter(2);
            loadData(2);
        });


        floatingActionButton.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), InsertNotesActivity.class));
        });

        notesViewModel.getAllNotes.observe(this, new Observer<List<Notes_entity>>() {
            @Override
            public void onChanged(List<Notes_entity> notes_entities) {
                setAdapter(notes_entities);
            }
        });

    }

    private void loadData(int i) {
        if(i == 0){
            notesViewModel.getAllNotes.observe(this, new Observer<List<Notes_entity>>() {
                @Override
                public void onChanged(List<Notes_entity> notes_entities) {
                    setAdapter(notes_entities);
                }
            });
        }
        else if(i == 1){
            notesViewModel.highToLow.observe(this, new Observer<List<Notes_entity>>() {
                @Override
                public void onChanged(List<Notes_entity> notes_entities) {
                    setAdapter(notes_entities);
                }
            });
        }
        else if(i == 2 ){
            notesViewModel.lowToHigh.observe(this, new Observer<List<Notes_entity>>() {
                @Override
                public void onChanged(List<Notes_entity> notes_entities) {
                    setAdapter(notes_entities);
                }
            });
        }
    }

    public  void filter(int id){
        if(id == 0){
            noFilter.setBackgroundResource(R.drawable.filter_selected_shape);
            hightToLow.setBackgroundResource(R.drawable.filter_unselected_shape);
            lowToHigh.setBackgroundResource(R.drawable.filter_unselected_shape);
        }
        else if(id == 1){
            noFilter.setBackgroundResource(R.drawable.filter_unselected_shape);
            hightToLow.setBackgroundResource(R.drawable.filter_selected_shape);
            lowToHigh.setBackgroundResource(R.drawable.filter_unselected_shape);
        }
        else if (id == 2){
            noFilter.setBackgroundResource(R.drawable.filter_unselected_shape);
            hightToLow.setBackgroundResource(R.drawable.filter_unselected_shape);
            lowToHigh.setBackgroundResource(R.drawable.filter_selected_shape);
        }
    }
    
    public void setAdapter(List<Notes_entity> notes_entities){
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        notesAdapter = new NotesAdapter(getApplicationContext(), notes_entities);
        recyclerView.setAdapter(notesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.am_menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        return true;
    }

    private void NotesFilter(String newText) {
        //Log.e("@@@@@", "error");
    }


}