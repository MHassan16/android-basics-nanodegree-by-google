package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.habittracker.HabitContract.HabitEntry;

public class MainActivity extends AppCompatActivity {
    private com.example.android.habittracker.HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new com.example.android.habittracker.HabitDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
    private Cursor readMethod(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_NAME,
                HabitEntry.COLUMN_TIME,
                HabitEntry.COLUMN_HABIT};

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        return cursor;

    }
    private void displayDatabaseInfo() {


        Cursor read = readMethod();

        TextView displayView = (TextView) findViewById(R.id.text_view_habit);

        try {

            displayView.setText("The habit table contains " + read.getCount() + " habits.\n\n");
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_NAME + " - " +
                    HabitEntry.COLUMN_TIME + " - " +
                    HabitEntry.COLUMN_HABIT + "\n");

            int idColumnIndex = read.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = read.getColumnIndex(HabitEntry.COLUMN_NAME);
            int hoursColumnIndex = read.getColumnIndex(HabitEntry.COLUMN_TIME);
            int habitColumnIndex = read.getColumnIndex(HabitEntry.COLUMN_HABIT);

            while (read.moveToNext()) {

                int currentID = read.getInt(idColumnIndex);
                String currentName = read.getString(nameColumnIndex);
                String currentHours = read.getString(hoursColumnIndex);
                String currentHabit = read.getString(habitColumnIndex);

                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentHours + " - " +
                        currentHabit));
            }
        } finally {
            read.close();
        }
    }

    private void insertHabit() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_NAME, "Mostafa");
        values.put(HabitEntry.COLUMN_TIME, "10");
        values.put(HabitEntry.COLUMN_HABIT, "Football");

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {

            Toast.makeText(this, "Error with saving Habit", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(this, "Habit saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_insert_dummy_data:
                insertHabit();
                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_habit, menu);
        return true;
    }
}
