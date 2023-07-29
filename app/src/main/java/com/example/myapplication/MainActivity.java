package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;


//import com.example.myapplication.R;
//import com.project.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading Pet Monitoring App...");
        progressDialog.setCancelable(false);

        // Start the progress dialog
        progressDialog.show();

        // Delay for 2 seconds before launching the next activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Launch the next activity
                Intent intent = new Intent(MainActivity.this, home.class);
                startActivity(intent);

                // Close the progress dialog
                progressDialog.dismiss();

                // Finish the current activity
                finish();
            }
        }, 1000);
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