package com.example.android.habittracker;

import android.provider.BaseColumns;

/**
 * Created by wolfgang on 04.07.16.
 */
public class HabitTrackerContract
{
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public HabitTrackerContract() {}

    /* Inner class that defines the table contents */
    public static abstract class HabitEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "habit";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_STARTINGDATE = "startingdate";
        public static final String COLUMN_NAME_STREAK = "streak";
    }
}
