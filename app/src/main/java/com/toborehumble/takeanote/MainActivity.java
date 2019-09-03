package com.toborehumble.takeanote;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Communication {
    private NoteDataManager noteDataManager;
    private NewNoteDialog newNoteDialog;

    FloatingActionButton floatingActionButton;

    private static final int RECORD_REQUEST_CODE = 123;
    private static final int FILE_REQUEST_CODE = 456;
    private static final int STORAGE_REQUEST_CODE = 789;
    private static final int CAMERA_REQUEST_CODE = 101;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Toast.makeText(this, "Land", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setOrientation("portrait");
        newNoteDialog = new NewNoteDialog();

        noteDataManager = new NoteDataManager(MainActivity.this);
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
        handleFab();

        requestFilePerm();
        requestMediaPerm();
        requestStorePerm();
        requestCameraPerm();
    }

    private void setOrientation(String orientation){
        if(orientation.equals("portrait")){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else if(orientation.equals("landscape")){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    private void handleFab() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newNoteDialog.setStyle(DialogFragment.STYLE_NORMAL, DialogFragment.STYLE_NORMAL);
                newNoteDialog.show(getSupportFragmentManager(), "new_note_dialog");
            }
        });
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }
        return true;
    }*/

    @Override
    public void addNote(Note note) {
        noteDataManager.addNote(note);
        NotesFragment.noteArrayList.add(note);
        NotesFragment.noteAdapter.notifyDataSetChanged();
        Toast.makeText(this, note.getTitle() + " added.", Toast.LENGTH_LONG).show();
    }

    private void requestStorePerm() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "This permission is needed.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        RECORD_REQUEST_CODE);
            }
        } else {
            Toast.makeText(MainActivity.this, "Permission granted.", Toast.LENGTH_LONG).show();
        }
    }

    private void requestMediaPerm() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.RECORD_AUDIO)) {
                Toast.makeText(MainActivity.this, "This permission is needed.",
                        Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_REQUEST_CODE);
            }
        } else {
            //Toast.makeText(MainActivity.this, "Permission granted.", Toast.LENGTH_LONG).show();
        }
    }

    private void requestFilePerm() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "This permission is needed.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE}, FILE_REQUEST_CODE);
            }
        } else {
            //Toast.makeText(this, "Permission granted.", Toast.LENGTH_LONG).show();
        }
    }

    private void requestCameraPerm() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.CAMERA)) {
                Toast.makeText(this, "This permission is needed.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
            }
        } else {
            //Toast.makeText(this, "Permission granted.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case RECORD_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(MainActivity.this, "Permission denied", Toast.LENGTH_SHORT)
                    // .show();
                }
                return;
            }
            case FILE_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case STORAGE_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case CAMERA_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
