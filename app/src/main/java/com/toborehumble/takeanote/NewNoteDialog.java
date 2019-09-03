package com.toborehumble.takeanote;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewNoteDialog extends DialogFragment {
    private EditText new_note_title;
    private EditText new_note_body;
    Button save_new_note;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_new_note, container, false);
        new_note_body = view.findViewById(R.id.new_category_body_edit_text);
        new_note_title = view.findViewById(R.id.new_category_title_edit_text);
        save_new_note = view.findViewById(R.id.btn_save_note);

        save_new_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Communication communication = (Communication) getContext();
                Note note = new Note();
                note.setTitle(new_note_title.getText().toString());
                note.setBody(new_note_body.getText().toString());
                Date date = new Date();
                Date time = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("E d M',' y");
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:ss a");
                note.setDate(dateFormat.format(date));
                note.setTime(timeFormat.format(time));
                note.setCreated(String.valueOf(new Date().getTime()));

                communication.addNote(note);
                dismiss();
            }
        });
        return view;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
