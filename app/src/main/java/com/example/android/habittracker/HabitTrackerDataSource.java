package com.example.android.habittracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by wolfgang on 04.07.16.
 */
public class HabitTrackerDataSource
{
    private SQLiteDatabase mDatabase;
    private HabitTrackerDbHelper mHelper;


    public HabitTrackerDataSource(Context context)
    {
        Log.d("HabitTrackerDataSource", "create dbHelper");
        mHelper = new HabitTrackerDbHelper(context);
    }
}