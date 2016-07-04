package com.example.android.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by wolfgang on 04.07.16.
 */
public class HabitTrackerDataSource
{
    private SQLiteDatabase mDatabase;
    private HabitTrackerDbHelper mHelper;
    private final String LOG_TAG = "HabitTrackerDataSource";

    private String[] mColumns = {
            HabitTrackerContract.HabitEntry.COLUMN_NAME_ID,
            HabitTrackerContract.HabitEntry.COLUMN_NAME_TITLE,
            HabitTrackerContract.HabitEntry.COLUMN_NAME_STARTINGDATE,
            HabitTrackerContract.HabitEntry.COLUMN_NAME_STREAK
    };

    public HabitTrackerDataSource(Context context)
    {
        Log.d("HabitTrackerDataSource", "create dbHelper");
        mHelper = new HabitTrackerDbHelper(context);
    }

    public void open()
    {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        mDatabase = mHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + mDatabase.getPath());
    }

    public void close()
    {
        mHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    public Habit createHabit(String aHabitName, String aStartDate)
    {
        ContentValues values = new ContentValues();
        values.put(HabitTrackerContract.HabitEntry.COLUMN_NAME_TITLE, aHabitName);
        values.put(HabitTrackerContract.HabitEntry.COLUMN_NAME_STARTINGDATE, aStartDate);
        values.put(HabitTrackerContract.HabitEntry.COLUMN_NAME_STREAK, 0);

        long insertId = mDatabase.insert(HabitTrackerContract.HabitEntry.TABLE_NAME, null, values);

        Cursor cursor = mDatabase.query(
                HabitTrackerContract.HabitEntry.TABLE_NAME,
                mColumns,
                HabitTrackerContract.HabitEntry.COLUMN_NAME_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Habit habit = createHabitForCursor(cursor);
        cursor.close();

        return habit;
    }

    private Habit createHabitForCursor(Cursor cursor)
    {
        int idIndex = cursor.getColumnIndex(HabitTrackerContract.HabitEntry.COLUMN_NAME_ID);
        int idTitle = cursor.getColumnIndex(HabitTrackerContract.HabitEntry.COLUMN_NAME_TITLE);
        int idStartDate = cursor.getColumnIndex(HabitTrackerContract.HabitEntry.COLUMN_NAME_STARTINGDATE);
        int idStreak = cursor.getColumnIndex(HabitTrackerContract.HabitEntry.COLUMN_NAME_STREAK);

        long id = cursor.getLong(idIndex);
        String title = cursor.getString(idTitle);
        String startDate = cursor.getString(idStartDate);
        int streak = cursor.getInt(idStreak);

        Habit habit = new Habit(id, title, startDate, streak);

        return habit;
    }



}