package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity7 extends AppCompatActivity {
    Button b1,b2,b4,b5;
    int c1 = 0;
    int c2 = 0;
    int c4 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"You have voted for Pravin",Toast.LENGTH_SHORT).show();
                c1++;
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"You have voted for Dharsan",Toast.LENGTH_SHORT).show();
                c2++;
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"You have voted for Naveen",Toast.LENGTH_SHORT).show();
                c4++;
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity8.class);
                i.putExtra("c1",c1);
                i.putExtra("c2",c2);
                i.putExtra("c4",c4);
                startActivity(i);
            }
        });

    }
}