package com.example.android.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{
    private HabitTrackerDataSource mDataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Habit testHabit = new Habit("Push-Ups", "2016-01-01", 0, 0);

        mDataSource = new HabitTrackerDataSource(this);
    }
}
