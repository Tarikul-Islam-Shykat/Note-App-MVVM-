package com.example.notesappmvvm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesappmvvm.Activity.UpdateNotesActivity;
import com.example.notesappmvvm.Model.Notes_entity;
import com.example.notesappmvvm.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {

    Context context;
    List<Notes_entity>notes;
    List<Notes_entity> search_notes_list;

    public NotesAdapter(Context applicationContext, List<Notes_entity> notes_entities) {
        this.context = applicationContext;
        this.notes = notes_entities;
        search_notes_list = new ArrayList<>(notes_entities);
    }

    public  void searchNotes(List<Notes_entity> filteredNotes){
        this.notes = filteredNotes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public notesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(context).inflate(R.layout.sample_item_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull notesViewHolder holder, int position) {
        Notes_entity note_ = notes.get(position);

       // Toast.makeText(context, ""+note_.notesTitle, Toast.LENGTH_SHORT).show();
        holder.title.setText(note_.notesTitle.toString());
        holder.subTitle.setText(note_.notesSubtitle);
        holder.noteDate.setText(note_.notesData);

        switch (note_.notesPriority) {
            case "1":
                holder.notesPrioroity.setBackgroundResource(R.drawable.green_shape);
                break;
            case "2":
                holder.notesPrioroity.setBackgroundResource(R.drawable.yellow_shape);
                break;
            case "3":
                holder.notesPrioroity.setBackgroundResource(R.drawable.red_shape);
                break;
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateNotesActivity.class);
            intent.putExtra("id",note_.id);
            intent.putExtra("title",note_.notesTitle);
            intent.putExtra("subtitle", note_.notesSubtitle);
            intent.putExtra("priority", note_.notesPriority);
            intent.putExtra("notes", note_.notes);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class notesViewHolder extends RecyclerView.ViewHolder{

        TextView title, subTitle, noteDate;
        View notesPrioroity;

        public notesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_sampleNotes_title);
            subTitle = itemView.findViewById(R.id.txt_sampleNotes_subTitle);
            noteDate = itemView.findViewById(R.id.txt_sampleNotes_Dates);
            notesPrioroity = itemView.findViewById(R.id.view_sampleNotes_prior);
        }
    }
}
