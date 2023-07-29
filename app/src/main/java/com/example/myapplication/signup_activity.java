package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.regex.Pattern;

public class signup_activity extends AppCompatActivity {
    Button signup;
    EditText email,pass,cpass,contact;
    boolean flg = true;
    private DatabaseReference databaseref;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{8,}" +                // at least 4 characters
                    "$");
    ProgressDialog pg;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup = findViewById(R.id.button3);
        email = findViewById(R.id.editTextTextPersonName3);
        pass = findViewById(R.id.editTextTextPersonName2);
        cpass = findViewById(R.id.editTextTextPersonName);
        contact = findViewById(R.id.editTextTextPersonName4);
        databaseref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://petmonitor-46d37-default-rtdb.firebaseio.com/");
        pg = new ProgressDialog(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"Email cannot be empty",Toast.LENGTH_LONG).show();
                String emailid = email.getText().toString();
                String p = pass.getText().toString();
                String cp = cpass.getText().toString();
                String c = contact.getText().toString();
                if(emailid.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Email cannot be empty",Toast.LENGTH_LONG).show();
                }
                else if(p.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Password cannot be empty",Toast.LENGTH_LONG).show();
                }
                else if(cp.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Password cannot be empty",Toast.LENGTH_LONG).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(emailid).matches())
                {
                    {
                        Toast.makeText(getApplicationContext(),"Please enter valid Email Id",Toast.LENGTH_LONG).show();
                    }
                }
                else if (!PASSWORD_PATTERN.matcher(p).matches()) {
                    Toast.makeText(getApplicationContext(),"Password must contain atleast 1 special character and 8 characters long",Toast.LENGTH_LONG).show();
                }
                else if(!p.equals(cp))
                {
                    Toast.makeText(getApplicationContext(),"Password should match",Toast.LENGTH_LONG).show();
                }
                else {
                    pg.setMessage("Please Wait...");
                    pg.setTitle("Registration");
                    pg.setCanceledOnTouchOutside(false);
                    pg.show();
//                    mAuth.createUserWithEmailAndPassword(emailid,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            pg.dismiss();
//                            Toast.makeText(getApplicationContext(),"Registration successfull",Toast.LENGTH_LONG).show();
//                        }
//                    });
                    HashMap<String, String> users = new HashMap<>();
                    users.put("Email", emailid);
                    databaseref.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(c))
                            {
                                pg.dismiss();
                                Toast.makeText(getApplicationContext(), "You have already registered", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                databaseref.child("users").child(c).child("Email").setValue(emailid);
                                databaseref.child("users").child(c).child("Password").setValue(p);
                                pg.dismiss();
                                Toast.makeText(getApplicationContext(), "Registration Succesful", Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

//                    users.put("Password", p);
//                    if ()
//                    {
//                        databaseref.push().child(c).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                pg.dismiss();
//                                Toast.makeText(getApplicationContext(), "Registration successfull", Toast.LENGTH_LONG).show();
//                            }
//                        });
//                }
//                    else {
//                        pg.dismiss();
//                        Toast.makeText(getApplicationContext(), "You have already registered", Toast.LENGTH_LONG).show();
//
//                    }

//                    pg.dismiss();

                }
            }
        });
    }
    public boolean check(String email)
    {

        databaseref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren())
                {
                    //System.out.println(email);
                    if(email.equals(snap.child("Email").getValue().toString())){
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