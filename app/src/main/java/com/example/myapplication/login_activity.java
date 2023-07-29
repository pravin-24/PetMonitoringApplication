package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.project.myapplication.R;

public class login_activity extends AppCompatActivity {
    EditText email,pass;
    Button login;
    boolean flg = true;
    private DatabaseReference databaseref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.editTextTextPersonName3);
        pass = findViewById(R.id.editTextTextPersonName2);
        login = findViewById(R.id.button3);
        databaseref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://petmonitor-46d37-default-rtdb.firebaseio.com/");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String c = email.getText().toString();
                String p = pass.getText().toString();
                if(c.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Mobile Number cannot be empty",Toast.LENGTH_LONG).show();
                }
                else if(p.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Password cannot be empty",Toast.LENGTH_LONG).show();
                }
                else
                {
                    databaseref.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(c))
                            {
                                String password = snapshot.child(c).child("Password").getValue(String.class);
                                System.out.println(password);
                                if(p.equals(password)) {
                                    Toast.makeText(getApplicationContext(), "You have successfully logged in", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                                    startActivity(i);
                                }
                                else
                                    Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "First Sign up", Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });
    }
    public boolean check(String email,String pass)
    {

        databaseref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren())
                {
                    //System.out.println(email);
                    if(email.equals(snap.child("Email").getValue().toString()) && pass.equals(snap.child("Password").getValue().toString())){
                        System.out.println(email);
                        flg = false;
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return flg;
    }
}