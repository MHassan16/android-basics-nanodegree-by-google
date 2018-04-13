package com.example.android.reportcard;

import static android.R.attr.id;

public class ReportCard {
    private String mName, mCourse;
    private int mID, mGrade;

    public ReportCard(String name, String Course, int Id, int grade) {
        mName = name;
        mCourse = Course;
        mID = id;
        mGrade = grade;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmGrade() {
        return mGrade;
    }

    public void setmGrade(int mGrade) {
        this.mGrade = mGrade;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmCourse() {
        return mCourse;
    }

    public void setmCourse(String mCourse) {
        this.mCourse = mCourse;
    }

    public String getLetter(int grade) {
        String Letter;
        if (grade >= 90 && grade <= 100) {
            Letter = "A";
        } else if (grade >= 80 && grade <= 89) {
            Letter = "B";
        } else if (grade >= 70 && grade <= 79) {
            Letter = "C";
        } else if (grade >= 65 && grade <= 69) {
            Letter = "D";
        } else {
            Letter = "F";
        }

        return Letter;
    }

    @Override
    public String toString() {
        return "ReportCard{" +
                "mName='" + mName + '\'' +
                ", mCourse='" + mCourse + '\'' +
                ", mID=" + mID +
                ", mGrade=" + mGrade +
                '}';
    }

}
