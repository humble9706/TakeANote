package com.toborehumble.takeanote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class CustomSqlOpenHelper extends SQLiteOpenHelper {
    private Context context;
    private String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    private static final String DB_NAME = "ONE_NOTE_DB";
    private static final int DB_VERSION = 1;

    private static final String NOTE_TABLE = "NOTE_TABLE";
    public static final String NOTE_ID = "_id";
    public static final String NOTE_TITLE = "note_title";
    public static final String NOTE_BODY = "note_body";
    public static final String NOTE_DATE = "note_date";
    public static final String NOTE_TIME = "note_time";
    public static final String NOTE_CREATED = "note_created";

    public CustomSqlOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //notes table creation query
        String createNoteTableQuery = "CREATE TABLE " + NOTE_TABLE + " (" +
                NOTE_ID + " integer primary key autoincrement not null," +
                NOTE_TITLE + " text not null," +
                NOTE_BODY + " text not null," +
                NOTE_DATE + " text not null," +
                NOTE_TIME + " text not null," +
                NOTE_CREATED + " text not null" + " );";

        db.execSQL(createNoteTableQuery);
        Toast.makeText(context, "Notes Table Created", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
