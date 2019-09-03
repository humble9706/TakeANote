package com.toborehumble.takeanote;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class NotesFragment extends Fragment implements Listener {
    public static ArrayList<Note> noteArrayList;
    RecyclerView recyclerView;
    public static NoteAdapter noteAdapter;
    NoteDataManager dataManager;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteArrayList = new ArrayList<>();
        noteAdapter = new NoteAdapter();
        layoutManager = new GridLayoutManager(getContext(), 1);
        noteAdapter.setContext(getContext());
        dataManager = new NoteDataManager(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        recyclerView = view.findViewById(R.id.notesRecyclerView);
        recyclerView.setLayoutManager(layoutManager);
        noteArrayList = makeNotes();
        noteAdapter.setNoteArrayList(noteArrayList);
        recyclerView.setAdapter(noteAdapter);
        return view;
    }

    public void updateLayout(){
        noteAdapter.notifyDataSetChanged();
    }

    public ArrayList<Note> makeNotes(){
        Cursor cursor = dataManager.getAllNotes();
        try {
            if (cursor.moveToFirst()) {
                int titleColumn = cursor.getColumnIndexOrThrow(NoteDataManager.NOTE_TITLE);
                int bodyColumn = cursor.getColumnIndexOrThrow(NoteDataManager.NOTE_BODY);
                int dateColumn = cursor.getColumnIndexOrThrow(NoteDataManager.NOTE_DATE);
                int timeColumn = cursor.getColumnIndexOrThrow(NoteDataManager.NOTE_TIME);
                int createdColumn = cursor.getColumnIndexOrThrow(NoteDataManager.NOTE_CREATED);
                do{
                    Note note = new Note();

                    String title = cursor.getString(titleColumn);
                    String body = cursor.getString(bodyColumn);
                    String date = cursor.getString(dateColumn);
                    String time = cursor.getString(timeColumn);
                    String created = cursor.getString(createdColumn);

                    note.setBody(body);
                    note.setTitle(title);
                    note.setDate(date);
                    note.setTime(time);
                    note.setCreated(created);

                    noteArrayList.add(note);
                }
                while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }
        return noteArrayList;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void update() {
        noteAdapter.notifyDataSetChanged();
    }
}
