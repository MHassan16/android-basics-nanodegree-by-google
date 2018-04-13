package com.example.android.bookinglist;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mostafa on 11/29/2017.
 */

public class Booking implements Parcelable {
    String mTitle, mAuthor;

    public Booking(String Title, String Author) {
        mTitle = Title;
        mAuthor = Author;
    }

    private Booking(Parcel in) {
        mTitle = in.readString();
        mAuthor = in.readString();
    }

    public static final Creator<Booking> CREATOR = new Creator<Booking>() {
        @Override
        public Booking createFromParcel(Parcel in) {
            return new Booking(in);
        }


        public Booking[] newArray(int size) {
            return new Booking[size];
        }
    };

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mAuthor);
        out.writeString(mTitle);

    }

}
