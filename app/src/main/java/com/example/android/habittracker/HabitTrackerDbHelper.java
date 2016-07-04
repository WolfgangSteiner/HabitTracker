package com.example.android.habittracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HabitTrackerDbHelper extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "HabitTracker.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + HabitTrackerContract.HabitEntry.TABLE_NAME + " (" +
                    HabitTrackerContract.HabitEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    HabitTrackerContract.HabitEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    HabitTrackerContract.HabitEntry.COLUMN_NAME_STARTINGDATE + TEXT_TYPE + COMMA_SEP +
                    HabitTrackerContract.HabitEntry.COLUMN_NAME_STREAK + INTEGER_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + HabitTrackerContract.HabitEntry.TABLE_NAME;


    public HabitTrackerDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("HabitTrackerDbHelper", "Created database:" + getDatabaseName());
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i("HabitTrackerDbHelper:", "Creating table!");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}