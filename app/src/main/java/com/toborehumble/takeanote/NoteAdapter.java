package com.toborehumble.takeanote;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Note> noteArrayList;

    public NoteAdapter(){}

    public NoteAdapter(Context context, ArrayList<Note> noteArrayList){
        this.context = context;
        this.noteArrayList = noteArrayList;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setNoteArrayList(ArrayList<Note> noteArrayList) {
        this.noteArrayList = noteArrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView date;
        private TextView body;
        private TextView time;
        private TextView created;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            body = itemView.findViewById(R.id.note_body);
            date = itemView.findViewById(R.id.note_date);
            time = itemView.findViewById(R.id.note_time);
            //created = itemView.findViewById(R.id.note_created);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.single_note, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.title.setText("Title: " + noteArrayList.get(position).getTitle());
        viewHolder.date.setText("date: " + noteArrayList.get(position).getDate());
        viewHolder.body.setText(noteArrayList.get(position).getBody());
        viewHolder.time.setText("time: " + noteArrayList.get(position).getTime());
        //viewHolder.created.setText(noteArrayList.get(position).getCreated());
    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }
}
