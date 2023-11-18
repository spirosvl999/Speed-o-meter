package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

public class MainActivity4 extends AppCompatActivity {

    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }
    public void Show(View view)                                                                     // At activity_main4.xml we said to our "Show History button to use
    {                                                                                               // "Show" function when it's been clicked.
        Cursor cursor = database.rawQuery("Select * from Violation",null);           // We are selecting our "Violation" database
        StringBuilder data = new StringBuilder();                                                   // We convert everything on String and we use ea new function named showMessage.
        while (cursor.moveToNext())
        {
            data.append("Latitude: "+cursor.getString(0)+"\n");
            data.append("Longitude: "+cursor.getString(1)+"\n");
            data.append("Speed: "+cursor.getString(2)+"\n");
            data.append("Timestamp: "+cursor.getString(3)+"\n");
            data.append("-----------------\n");
        }
        showMessage("Violations", data.toString());                                            // At the "showMessage" function
    }                                                                                               // We are creating a alert dialog
                                                                                                    // show the user
    private void showMessage(String Title, String message)                                          // all the violations that happened.
    {
        new AlertDialog.Builder(this)
                .setTitle(Title)
                .setMessage(message)
                .show();
    }
}