package com.toborehumble.takeanote;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class NoteDataManager {
    private SQLiteDatabase database;

    private static final String NOTE_TABLE = "NOTE_TABLE";
    public static final String NOTE_ID = "_id";
    public static final String NOTE_TITLE = "note_title";
    public static final String NOTE_BODY = "note_body";
    public static final String NOTE_DATE = "note_date";
    public static final String NOTE_TIME = "note_time";
    public static final String NOTE_CREATED = "note_created";


    public NoteDataManager(Context context) {
        CustomSqlOpenHelper customSqlOpenHelper = new CustomSqlOpenHelper(context);
        database = customSqlOpenHelper.getWritableDatabase();
    }

    public void addNote(Note note) {
        String addNoteQuery =
                "INSERT INTO " + NOTE_TABLE + " (" +
                        NOTE_TITLE + ", " +
                        NOTE_BODY + ", " +
                        NOTE_DATE + ", " +
                        NOTE_TIME + ", " +
                        NOTE_CREATED + ") " +
                        "VALUES (" + "'" +
                        note.getTitle() + "'" + ", " + "'" +
                        note.getBody() + "'" + ", " + "'" +
                        note.getDate() + "'" + "," + "'" +
                        note.getTime() + "'" + "," + "'" +
                        note.getCreated() + "'" + ");";

        database.execSQL(addNoteQuery);
    }

    public Cursor getSingleNote(int noteId){
        String getSingleNoteQuery =
                "SELECT * from " + NOTE_TABLE + " WHERE " + NOTE_ID + " = " + noteId;
        Cursor cursor = database.rawQuery(getSingleNoteQuery,null);
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor getAllNotes() {
        String getAllNotesQuery = "SELECT * FROM " + NOTE_TABLE;
        Cursor cursor = database.rawQuery(getAllNotesQuery, null);
        cursor.moveToFirst();
        return cursor;
    }

    public void updateNote(Note note){
        String updateNoteQuery = "";
    }

    public void deleteNote(Note note){
        String deleteNoteQuery = "";
    }

    public Cursor getNotesInACategory(int categoryId){
        String getNotesInCategoryQuery = "";
        Cursor cursor = database.rawQuery(getNotesInCategoryQuery, null);
        cursor.moveToFirst();
        return cursor;
    }
}
