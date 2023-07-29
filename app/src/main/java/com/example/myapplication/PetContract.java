package com.example.myapplication;

import android.provider.BaseColumns;

public final class PetContract {

    private PetContract() {}

    public static class PetEntry implements BaseColumns {
        public static final String TABLE_NAME = "pets";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_BREED = "breed";
        public static final String COLUMN_AGE = "age";
    }

}
