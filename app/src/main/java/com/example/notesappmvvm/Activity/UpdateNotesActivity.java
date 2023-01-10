package com.example.notesappmvvm.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesappmvvm.MainActivity;
import com.example.notesappmvvm.Model.Notes_entity;
import com.example.notesappmvvm.R;
import com.example.notesappmvvm.ViewModel.NotesViewModel;
import com.example.notesappmvvm.databinding.ActivityMainBinding;
import com.example.notesappmvvm.databinding.ActivityUpdateNotesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {

    ActivityUpdateNotesBinding updateNotesBinding;
    String priority = "1";
    String s_title, s_subtitle, s_notes, s_priority;

    NotesViewModel notesViewModel;

    int s_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateNotesBinding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(updateNotesBinding.getRoot());
        getSupportActionBar().setTitle("Update Notes");

        s_title = getIntent().getStringExtra("title");
        s_subtitle = getIntent().getStringExtra("subtitle");
        s_notes = getIntent().getStringExtra("notes");
        s_priority = getIntent().getStringExtra("priority");
        s_id= getIntent().getIntExtra("id",0); // 0 is default value, in case we dont get any value.

        updateNotesBinding.edtAUpdateSubTitle.setText(s_subtitle);
        updateNotesBinding.edtAUpdateTitle.setText(s_title);
        updateNotesBinding.edtAUpdateNotes.setText(s_notes);

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        updateNotesBinding.ivAUpdateGreenPrior.setOnClickListener(v -> {
            priority = "1";
            setPriorityMark_(priority);
        });

        updateNotesBinding.ivAUpdateRedPrior.setOnClickListener(v -> {
            priority = "3";
            setPriorityMark_(priority);
        });

        updateNotesBinding.ivAUpdateYellowPrior.setOnClickListener(v -> {
            priority = "2";
            setPriorityMark_(priority);
        });

        updateNotesBinding.btnAUpdateUpdate.setOnClickListener(v -> {
            String title, subtitle,notes;
            title = updateNotesBinding.edtAUpdateTitle.getText().toString();
            subtitle = updateNotesBinding.edtAUpdateSubTitle.getText().toString();
            notes = updateNotesBinding.edtAUpdateNotes.getText().toString();
            updateNotes(title, subtitle, notes);
        });

        switch (s_priority) {
            case "1":
                updateNotesBinding.ivAUpdateGreenPrior.setBackgroundResource(R.drawable.green_shape);
                break;
            case "2":
                updateNotesBinding.ivAUpdateYellowPrior.setBackgroundResource(R.drawable.yellow_shape);
                break;
            case "3":
                updateNotesBinding.ivAUpdateRedPrior.setBackgroundResource(R.drawable.red_shape);
                break;
        }


    }

    private void updateNotes(String title, String subtitle, String notes) {
        if(title.isEmpty()){
            Toast.makeText(this, "Please give a title", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Date date = new Date();
            CharSequence sequence = DateFormat.format("MMMM d, yyyy", date.getTime());

            Notes_entity updateNotes = new Notes_entity();
            updateNotes.id = s_id;
            updateNotes.notesTitle = title;
            updateNotes.notesSubtitle = subtitle;
            updateNotes.notes  = notes;
            updateNotes.notesPriority = priority;
            updateNotes.notesData = sequence.toString();
            notesViewModel.updateNote(updateNotes);

            Toast.makeText(this, "Notes updated successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public  void setPriorityMark_(String prior){

        if(prior.equals("1")){
            updateNotesBinding.ivAUpdateGreenPrior.setImageResource(R.drawable.ic_baseline_done_24);
            updateNotesBinding.ivAUpdateYellowPrior.setImageResource(0);
            updateNotesBinding.ivAUpdateRedPrior.setImageResource(0);
        }

        else if(prior.equals("2")){
            updateNotesBinding.ivAUpdateGreenPrior.setImageResource(0);
            updateNotesBinding.ivAUpdateYellowPrior.setImageResource(R.drawable.ic_baseline_done_24);
            updateNotesBinding.ivAUpdateRedPrior.setImageResource(0);
        }

        else if(prior.equals("3")){
            updateNotesBinding.ivAUpdateGreenPrior.setImageResource(0);
            updateNotesBinding.ivAUpdateYellowPrior.setImageResource(0);
            updateNotesBinding.ivAUpdateRedPrior.setImageResource(R.drawable.ic_baseline_done_24);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.updateMenu_delete){
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(UpdateNotesActivity.this, R.style.BottomSheetStyle);

            View view = LayoutInflater.from(getApplicationContext()).inflate(
                    R.layout.delete_bottom_sheet,
                    (LinearLayout)findViewById(R.id.bottom_sheet_delete)
            );

            bottomSheetDialog.setContentView(view);

            TextView yes, no;
            yes = view.findViewById(R.id.bottom_sheet_delete_YesBtn);
            no = view.findViewById(R.id.bottom_sheet_delete_NoBtn);

            yes.setOnClickListener(v -> {
                notesViewModel.deleteNote(s_id);
                startActivity(new Intent(UpdateNotesActivity.this, MainActivity.class));
                finish();
            });

            no.setOnClickListener(v->{
                bottomSheetDialog.dismiss();
            });

            bottomSheetDialog.show();

        }
        return true;
    }
}