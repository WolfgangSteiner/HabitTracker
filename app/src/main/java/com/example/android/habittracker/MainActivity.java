package com.example.android.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{
    private HabitTrackerDataSource mDataSource;
    private final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataSource = new HabitTrackerDataSource(this);

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        mDataSource.open();

        Habit habit = mDataSource.createHabit("Push-Ups", "2016-07-04");
        Log.d(LOG_TAG, "new habit: " + habit.toString());

        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        mDataSource.close();
    }
}
