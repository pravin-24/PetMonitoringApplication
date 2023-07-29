package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.myapplication.PetDatabaseHelper;
import com.example.myapplication.Pet;



public class MainActivity13 extends AppCompatActivity {
    private EditText nameEditText, breedEditText, ageEditText;
    private Button addPetButton;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);

        nameEditText = findViewById(R.id.name_edittext);
        breedEditText = findViewById(R.id.breed_edittext);
        ageEditText = findViewById(R.id.age_edittext);
        addPetButton = findViewById(R.id.add_button);

        PetDatabaseHelper dbHelper = new PetDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();


        addPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String breed = breedEditText.getText().toString().trim();
                String ageStr = ageEditText.getText().toString().trim();

                if (name.isEmpty() || breed.isEmpty() || ageStr.isEmpty()) {
                    Toast.makeText(MainActivity13.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                int age = Integer.parseInt(ageStr);

                ContentValues values = new ContentValues();
                values.put(PetContract.PetEntry.COLUMN_NAME, name);
                values.put(PetContract.PetEntry.COLUMN_BREED, breed);
                values.put(PetContract.PetEntry.COLUMN_AGE, age);

                long newRowId = db.insert(PetContract.PetEntry.TABLE_NAME, null, values);

                if (newRowId == -1) {
                    Toast.makeText(MainActivity13.this, "Error saving pet", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity13.this, "Pet saved with ID " + newRowId, Toast.LENGTH_SHORT).show();
                    nameEditText.getText().clear();
                    breedEditText.getText().clear();
                    ageEditText.getText().clear();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
