package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity12 extends AppCompatActivity {
    Button button;
    EditText name, feedback;
    SharedPreferences sp;
    String nameStr, feedStr, nameReceive, feedReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);
        sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        name = findViewById(R.id.name_edittext);
        feedback = findViewById(R.id.feedback_edittext);
        button = findViewById(R.id.submit_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameStr = name.getText().toString();
                feedStr = feedback.getText().toString();
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name", nameStr);
                editor.putString("feedback", feedStr);
                editor.commit();
                sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);

                // Get the user's name and feedback from the SharedPreferences
                nameReceive = sp.getString("name", "");
                feedReceive = sp.getString("feedback", "");

                // Use the user's name and feedback as needed
                Toast.makeText(MainActivity12.this, "Thanks " + nameReceive + ", for your valuable feedback of " + feedReceive +
                        ". We will sort it.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
