package com.example.android.newsapp;

/**
 * Created by Mostafa on 12/10/2017.
 */

public class News {

    private String mTitle, mSection, mUrl, mDate,mAuthour;

    public News(String Title, String Section, String Url, String Date,String Author) {
        mTitle = Title;
        mAuthour=Author;
        mDate=Date;
        mSection = Section;
        mUrl = Url;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthour(){
        return mAuthour;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmSection() {
        return mSection;

    }

    public String getmUrl() {
        return mUrl;
    }


}
