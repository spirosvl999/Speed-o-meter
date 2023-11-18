package com.example.hw1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import android.widget.LinearLayout;


import java.util.Locale;

public class MainActivity2 extends AppCompatActivity implements LocationListener
{
    TextView textView;
    SQLiteDatabase database;
    SharedPreferences sp = getApplicationContext().getSharedPreferences("Speed_Limit", Context.MODE_PRIVATE);
    Integer limit = sp.getInt("Speed", 0);

    Float temp_Sp;
    Long temp_time;
    Double temp_Lat, temp_Long;

    TextToSpeech tts;
    String text = "Slow Down";
    String  vi_speed, vi_time, vi_latitude, vi_longitude;
    LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        layout = findViewById(R.id.linearlayout);
        textView.setText(String.valueOf(0));                                                      // Text is the Shared Preference value
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener()                       // We Create our tts
        {                                                                                           // That's gonna work on English.
           @Override
           public void onInit(int i)
           {
               if (i != TextToSpeech.ERROR)
                   tts.setLanguage(Locale.ENGLISH);
           }
        });
    }

    @Override
    public void onLocationChanged(@NonNull Location location)
    {
         textView.setText(String.valueOf(location.getSpeed()));                                      // We are putting our speed on the Text View
        if (limit < location.getSpeed())
        {                                                                                           // In the case our speed is more than the limit,
            textView.setTextColor(Color.WHITE);                                                     // we are changing text color and background color
            layout.setBackgroundColor(Color.RED);                                                   // giving a message to the user to slow down
                                                                                                    // and we are using text to speech to tell the user to slow down.
            Toast.makeText(MainActivity2.this, "SLOW DOWN!", Toast.LENGTH_LONG).show();

            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);

            temp_Lat = location.getLatitude();
            vi_latitude = temp_Lat.toString();

            temp_Long = location.getLongitude();
            vi_longitude = temp_Long.toString();

            temp_Sp = location.getSpeed();
            vi_speed = temp_Sp.toString();

            temp_time = location.getTime();
            vi_time = temp_time.toString();

            database.execSQL("Insert or ignore into Violation Values(vi_latitude, vi_longitude, vi_speed, vi_time)");

        }

        else
        {
            textView.setTextColor(Color.BLACK);                                                     // When the speed goes to normal, we are getting the normal colors
            layout.setBackgroundColor(Color.WHITE);
        }
    }
}