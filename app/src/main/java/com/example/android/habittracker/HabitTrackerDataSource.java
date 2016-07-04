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
        Log.d(LOG_TAG, "Get reference to database.");
        mDatabase = mHelper.getWritableDatabase();
        mHelper.onCreate(mDatabase);
        Log.d(LOG_TAG, "Got reference to database. Path: " + mDatabase.getPath());
    }

    public void close()
    {
        mHelper.close();
        Log.d(LOG_TAG, "Database is closed by DBHelper.");
    }

    public Habit createHabit(String aHabitName, String aStartDate)
    {
        ContentValues values = new ContentValues();
        values.put(HabitTrackerContract.HabitEntry.COLUMN_NAME_TITLE, aHabitName);
        values.put(HabitTrackerContract.HabitEntry.COLUMN_NAME_STARTINGDATE, aStartDate);
        values.put(HabitTrackerContract.HabitEntry.COLUMN_NAME_STREAK, 0);

        long insertId = mDatabase.insert(HabitTrackerContract.HabitEntry.TABLE_NAME, null, values);

        return getHabit(insertId);
    }


    public Cursor getCursor(long aHabitId)
    {
        Cursor cursor = mDatabase.query(
                HabitTrackerContract.HabitEntry.TABLE_NAME,
                mColumns,
                HabitTrackerContract.HabitEntry.COLUMN_NAME_ID + "=" + aHabitId,
                null, null, null, null);

        cursor.moveToFirst();
        return cursor;
    }


    public Habit getHabit(long aHabitId)
    {
        return createHabitForCursor(getCursor(aHabitId));
    }

    public void updateStreak(Habit aHabit, int aStreak)
    {
        ContentValues cv = new ContentValues();
        cv.put(HabitTrackerContract.HabitEntry.COLUMN_NAME_STREAK, aStreak);

        mDatabase.update(
                HabitTrackerContract.HabitEntry.TABLE_NAME,
                cv,
                "? = ?",
                new String[] { HabitTrackerContract.HabitEntry.COLUMN_NAME_ID, Long.toString(aHabit.getHabitId())});

        aHabit.setStreak(aStreak);
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

    public void deleteAllHabits()
    {
        final String SQL_DELETE_ENTRIES =
                "DELETE FROM " + HabitTrackerContract.HabitEntry.TABLE_NAME;

        mDatabase.execSQL(SQL_DELETE_ENTRIES);
    }



}