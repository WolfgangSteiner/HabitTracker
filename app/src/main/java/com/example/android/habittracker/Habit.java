package com.example.android.habittracker;

/**
 * Created by wolfgang on 04.07.16.
 */
public class Habit
{
    public Habit(String aTitle, String aDateString, int aStreak, long aHabitId)
    {
        this.mTitle = aTitle;
        this.mDateString = aDateString;
        this.mStreak = aStreak;
        this.mHabitId = aHabitId;
    }

    private String mTitle;
    private String mDateString;
    private int mStreak;
    private long mHabitId;

    public String getTitle()
    {
        return mTitle;
    }

    public void setTitle(String mTitle)
    {
        this.mTitle = mTitle;
    }

    public String getDateString()
    {
        return mDateString;
    }

    public void setDateString(String mDateString)
    {
        this.mDateString = mDateString;
    }

    public int getStreak()
    {
        return mStreak;
    }

    public void setStreak(int mStreak)
    {
        this.mStreak = mStreak;
    }

    public long getHabitId()
    {
        return mHabitId;
    }

    public void setHabitId(long mHabitId)
    {
        this.mHabitId = mHabitId;
    }
}
