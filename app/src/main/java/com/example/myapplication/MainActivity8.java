package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity8 extends AppCompatActivity {
        TextView t1,t2,t3;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main8);
            t1 = findViewById(R.id.textView2);
            t2 = findViewById(R.id.textView3);
            t3 = findViewById(R.id.textView4);
            t1.setText("Result");
            int c1 = getIntent().getIntExtra("c1",0);
            int c2 = getIntent().getIntExtra("c2",0);
            int c4 = getIntent().getIntExtra("c4",0);
            ArrayList<Integer> al = new ArrayList<>();
            al.add(c1);
            al.add(c2);
            al.add(c4);
            ArrayList<Integer> al1 = new ArrayList<>(al);
            Collections.sort(al);
            int index = al1.indexOf(al.get(2));
            String s = "";
            switch(index)
            {
                case 0:
                    s = "The Winner is Pravin with "+al.get(2)+" votes";
                    break;
                case 1:
                    s = "The Winner is Dharsan with "+al.get(2)+" votes";
                    break;
                case 2:
                    s = "The Winner is Naveen with "+al.get(2)+" votes";
                    break;
            }
            t2.setText(s);
            t3.setText(" Pravin - "+c1+" votes \n Naveen - "+c4 +" votes \n Dharsan - "+c2 +" votes");

        }
    }