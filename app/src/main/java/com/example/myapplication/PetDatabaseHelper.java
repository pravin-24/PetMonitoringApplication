package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

public class PetDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pet_monitoring1.db";

    public PetDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PETS_TABLE = "CREATE TABLE pets ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "breed TEXT, " +
                "age INTEGER )";
        db.execSQL(CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS pets");
        this.onCreate(db);
    }

    public void addPet(Pet pet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", pet.getName());
        values.put("breed", pet.getBreed());
        values.put("age", pet.getAge());
        db.insert("pets", null, values);
        db.close();
    }

    public List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();
        String query = "SELECT * FROM pets ORDER BY name ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Pet pet = new Pet();
                pet.setId(Integer.parseInt(cursor.getString(0)));
                pet.setName(cursor.getString(1));
                pet.setBreed(cursor.getString(2));
                pet.setAge(Integer.parseInt(cursor.getString(3)));
                pets.add(pet);
            } while (cursor.moveToNext());
        }
        return pets;
    }

    public void deletePet(Pet pet) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("pets", "id = ?", new String[] { String.valueOf(pet.getId()) });
        db.close();
    }
}

