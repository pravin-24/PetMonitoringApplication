package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.myapplication.R;


public class MainActivity2 extends AppCompatActivity {
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10;
    private ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(MainActivity2.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait.");
        progressDialog.setCancelable(false);


        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent intent = new Intent(getBaseContext(), MainActivity4.class);
                startActivity(intent);
            }

        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent intent = new Intent(getBaseContext(), MainActivity9.class);
                startActivity(intent);
            }

        });

        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent intent = new Intent(getBaseContext(), MainActivity10.class);
                startActivity(intent);
            }

        });

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                ProgressDialog progressDialog = new ProgressDialog(MainActivity2.this);
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                Intent intent = new Intent(getBaseContext(), MainActivity5.class);
                startActivity(intent);

                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });


        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(takePictureIntent);
            }
        });
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.caringpets.org/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(intent);
            }

        });


        button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent intent = new Intent(getBaseContext(), MainActivity12.class);
                startActivity(intent);
            }

        });


        button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v1) {
            Intent intent = new Intent(getBaseContext(), MainActivity13.class);
            startActivity(intent);
        }

    });

        button10 = findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent intent = new Intent(getBaseContext(), more.class);
                startActivity(intent);
            }

        });
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item1:
                Toast.makeText(getApplicationContext(),"This is About App page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), MainActivity4.class);
                startActivity(intent);
                return true;
            case R.id.menu_item2:
                Toast.makeText(getApplicationContext(),"This is about Creators page", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getBaseContext(), MainActivity6.class);
                startActivity(intent1);
                return true;
            case R.id.menu_item3:
                Toast.makeText(getApplicationContext(),"Login Page !", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getBaseContext(), MainActivity3.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


}
