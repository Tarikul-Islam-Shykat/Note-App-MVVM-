package com.example.notesappmvvm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.example.notesappmvvm.Model.Notes_entity;
import com.example.notesappmvvm.R;
import com.example.notesappmvvm.ViewModel.NotesViewModel;
import com.example.notesappmvvm.databinding.ActivityInsertNotesBinding;

import java.util.Date;

public class InsertNotesActivity extends AppCompatActivity {

    ActivityInsertNotesBinding binding;
    String title, subtitle, notes;
    NotesViewModel notesViewModel;

    String priority = "1"; // 1 for green

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Add Notes");

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        binding.doneNotesBtn.setOnClickListener(v ->{
            title = binding.notesTitle.getText().toString();
            subtitle = binding.notesSubtitle.getText().toString();
            notes = binding.noteData.getText().toString();
            createNotes(title, subtitle, notes);
        });

        binding.redPrior.setOnClickListener(v->{
            priority = "3";
            setPriorityMark_(priority);
        });

        binding.greenPrior.setOnClickListener(v->{
            priority = "1";
            setPriorityMark_(priority);
        });


        binding.yellowPrior.setOnClickListener(v->{
            priority = "2";
            setPriorityMark_(priority);
        });


    }

    private void createNotes(String title, String subtitle, String notes) {

        if(title.isEmpty()){
            Toast.makeText(this, "Please give a title", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Date date = new Date();
            CharSequence sequence = DateFormat.format("MMMM d, yyyy", date.getTime());

            Notes_entity notes1 = new Notes_entity();

            notes1.notesTitle = title;
            notes1.notesSubtitle = subtitle;
            notes1.notes  = notes;
            notes1.notesPriority = priority;
            notes1.notesData = sequence.toString();
            notesViewModel.insertNote(notes1);

            Toast.makeText(this, "Notes created successfully", Toast.LENGTH_SHORT).show();
        }


    }

    public  void setPriorityMark_(String prior){

        if(prior.equals("1")){
            binding.greenPrior.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellowPrior.setImageResource(0);
            binding.redPrior.setImageResource(0);
        }

        else if(prior.equals("2")){
            binding.greenPrior.setImageResource(0);
            binding.yellowPrior.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redPrior.setImageResource(0);
        }

        else if(prior.equals("3")){
            binding.greenPrior.setImageResource(0);
            binding.yellowPrior.setImageResource(0);
            binding.redPrior.setImageResource(R.drawable.ic_baseline_done_24);
        }


    }

}