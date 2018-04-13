package com.example.android.bookinglist;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mostafa on 11/29/2017.
 */

public class QueryUtils {

    private QueryUtils() {

    }

    public static String handleOfAuthors(JSONArray authors) throws JSONException {
        String authorsList = null;

        if (authors.length() == 0) {
            return null;
        }
        for (int i = 0; i < authors.length(); i++) {
            if (i == 0) {
                authorsList = authors.getString(0);
            } else {
                authorsList += ", " + authors.getString(i);
            }
        }
        return authorsList;
    }


    public static List<Booking> extractBooks(String bookJason) {
        List<Booking> books = new ArrayList<>();
        String authors = "";
        try {
            JSONObject jsonResponse = new JSONObject(bookJason);
            if (jsonResponse.getInt("totalItems") == 0) {

                return books;
            }
            JSONArray jsonArray = jsonResponse.getJSONArray("items");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject currentBook = jsonArray.getJSONObject(i);
                JSONObject info = currentBook.getJSONObject("volumeInfo");
                String title = info.getString("title");

                if (info.has("authors")) {
                    JSONArray authorsArr = info.getJSONArray("authors");
                    authors = handleOfAuthors(authorsArr);
                }
                Booking book = new Booking(authors, title);
                books.add(book);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the Book JSON results", e);
        }
        return books;

    }
}
