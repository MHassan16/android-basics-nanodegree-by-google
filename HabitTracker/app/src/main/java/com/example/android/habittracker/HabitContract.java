package com.example.android.habittracker;

import android.provider.BaseColumns;

public class HabitContract {
    private HabitContract() {

    }

    public static final class HabitEntry implements BaseColumns {
        public final static String TABLE_NAME = "Habits";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_HABIT = "habit";
        public final static String COLUMN_TIME = "hours";
    }

}