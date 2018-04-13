package com.example.android.bookinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mostafa on 11/29/2017.
 */

public class BookingAdapter extends ArrayAdapter<Booking> {
    public BookingAdapter(Context context, ArrayList<Booking> bookList) {
        super(context, 0,bookList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Booking currentBook = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(currentBook.getmTitle());
        TextView author = (TextView) convertView.findViewById(R.id.author);
        author.setText(currentBook.getmAuthor());

        return convertView;
    }


}
