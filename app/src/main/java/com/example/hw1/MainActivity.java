package com.example.hw1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    Button buttonCurr;
    Button buttonLimit;
    Button buttonHis;
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCurr = findViewById(R.id.button);                                                     // We create 3 buttons with the options to make our program work.
        buttonLimit = findViewById(R.id.button2);
        buttonHis = findViewById(R.id.button3);
        database = openOrCreateDatabase("mydb.db", MODE_PRIVATE,null);

        database.execSQL("Create table if not exists Violation(" +
                "latitude Text," +                                                                  // We create our database
                "longitude Text," +                                                                 // to be used when there is a violation.
                "speed Text," +
                "timestamp Text Primary Key)");

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)  != PackageManager.PERMISSION_GRANTED)
        {                                                                                           // We are going to get permissions for the gps access
            ActivityCompat.requestPermissions(                                                      // that's gonna be used on the app.
                    this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);

            return;
        }

        buttonCurr.setOnClickListener(new View.OnClickListener()                                    // When a button is clicked
        {
            @Override
            public void onClick(View view)                                                          // We go to another activity.
            {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        buttonLimit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        buttonHis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, MainActivity4.class);
                startActivity(intent);
            }
        });

    }
}