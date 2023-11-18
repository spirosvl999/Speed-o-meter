package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button applyButton;
    SharedPreferences sp;
    Integer limit = sp.getInt("Speed", 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textView = (TextView) findViewById(R.id.textView5);
        editText = (EditText) findViewById(R.id.editText);
        applyButton = (Button) findViewById(R.id.button4);

        textView.setText(sp.getInt("Speed", 0));

        sp = getSharedPreferences("Speed_Limit", Context.MODE_PRIVATE);                        // We are using Shared Preferences for the speed limit to be used @ all activities.


        applyButton.setOnClickListener(new View.OnClickListener() {                                 // When the button is pressed
            @Override                                                                               // we are going to change the textview value
            public void onClick(View view)                                                          // and we are also putting the given value
            {                                                                                       // to the shared preferences
                textView.setText(editText.getText().toString());                                    // to make it work on the other activities
                String textViewValue = textView.getText().toString();

                SharedPreferences.Editor editor = sp.edit();

                editor.putInt("Speed", Integer.parseInt(textViewValue));
                editor.commit();
                Toast.makeText(MainActivity3.this, "Speed Limit Saved.", Toast.LENGTH_LONG).show();
            }                                                                                       // We are also giving a message to the user
        });                                                                                         // that the new speed limit is set.
    }
}