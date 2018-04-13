package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    float Result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private String getQuestion1() {
        EditText userInput = (EditText) findViewById(R.id.edit_text);
        String input_text = userInput.getText().toString();
        return input_text;
    }

    private void checkQuestion1() {
        String input_text = getQuestion1();
        if (input_text.trim().equalsIgnoreCase("Martin Keown")) {
            Result += 16.67;
        } else {
            Result += 0;
        }
    }

    private void checkQuestion2() {
        RadioButton thirdChoice = (RadioButton) findViewById(R.id.Radio_1_3);
        boolean isThirdChoice = thirdChoice.isChecked();
        if (isThirdChoice) {
            Result += 16.67;
        } else {
            Result += 0;
        }
    }

    private void checkQuesion3() {
        RadioButton thirdChoice = (RadioButton) findViewById(R.id.Radio_2_3);
        boolean isThirdChoice = thirdChoice.isChecked();
        if (isThirdChoice) {
            Result += 16.67;
        } else {
            Result += 0;
        }
    }

    private void checkQuestion4() {
        RadioButton thirdChoice = (RadioButton) findViewById(R.id.Radio_3_3);
        boolean isThirdChoice = thirdChoice.isChecked();
        if (isThirdChoice) {
            Result += 16.67;
        } else {
            Result += 0;
        }
    }

    private void checkQuestion5() {
        RadioButton firstChoice = (RadioButton) findViewById(R.id.Radio_4_1);
        boolean isFirstChoice = firstChoice.isChecked();
        if (isFirstChoice) {
            Result += 16.67;
        } else {
            Result += 0;
        }

    }

    private void checkQuestion6() {
        CheckBox firstChoice = (CheckBox) findViewById(R.id.cb_first_Clemence);
        CheckBox secondChoice = (CheckBox) findViewById(R.id.cb_second_Shilton);
        CheckBox thirdChoice = (CheckBox) findViewById(R.id.cb_third_Hart);
        CheckBox fourthChoice = (CheckBox) findViewById(R.id.cb_fourth_Corrigan);
        boolean isFirstChoice = firstChoice.isChecked();
        boolean isSecondChoice = secondChoice.isChecked();
        boolean isThirdChoice = thirdChoice.isChecked();
        boolean isFourthChoice = fourthChoice.isChecked();
        if (isFirstChoice && isSecondChoice && !isThirdChoice && isFourthChoice) {
            Result += 16.67;
        } else {
            Result += 0;
        }
    }

    private void resetResult() {
        Result = 0;
    }

    public void Submit(View view) {
        checkQuestion1();
        checkQuestion2();
        checkQuesion3();
        checkQuestion4();
        checkQuestion5();
        checkQuestion6();
        Toast.makeText(this, "Total Result :" + Math.floor(Result), Toast.LENGTH_LONG).show();
        resetResult();

    }
}