package com.example.android.bookinglist;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class BookingListActivity extends AppCompatActivity {

    BookingAdapter adapter;
    TextView txt;
    EditText editText;
    Button btn;
    public static final String LOG_TAG = BookingListActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);

        editText = (EditText) findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.search_button);
        txt = (TextView) findViewById(R.id.no_data);
        adapter = new BookingAdapter(this,new ArrayList<Booking>());

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkConnection()) {
                    BookingAsyncTask task = new BookingAsyncTask();
                    task.execute();
                } else {
                    Toast.makeText(BookingListActivity.this, R.string.noInternet, Toast.LENGTH_SHORT).show();
                }


            }
        });
        if (savedInstanceState != null) {
            Booking[] books = (Booking[]) savedInstanceState.getParcelableArray("Books Result");
            adapter.addAll(books);
        }

    }

    private void UpdateUI(List<Booking> books) {
        if (books.isEmpty()) {
            txt.setVisibility(View.VISIBLE);
        } else {
            txt.setVisibility(View.GONE);
        }

    }

    boolean isNetworkConnection() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }


    private String getUrlforRequest() {
        final String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=search+";
        String formatUserInput = editText.getText().toString().trim().replaceAll("\\s+", "+");
        String url = baseUrl + formatUserInput;
        return url;
    }

    private class BookingAsyncTask extends AsyncTask<URL, Void, List<Booking>> {


        protected List<Booking> doInBackground(URL... urls) {
            URL url = createUrl(getUrlforRequest());
            String jsonResponse = "";

            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            List<Booking> books = extractFromJson(jsonResponse);
            return books;
        }


        protected void onPostExecute(List<Booking> data) {

            if (data == null) {
                return;
            }
            UpdateUI(data);
            adapter.clear();
            adapter.addAll(data);
        }

    }

    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            exception.printStackTrace();
            return null;
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);


            } else {
                Log.e("BookingListActivity", "Error Response Code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String Line = reader.readLine();
            while (Line != null) {
                output.append(Line);
                Line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<Booking> extractFromJson(String bookingJson) {
        if (TextUtils.isEmpty(bookingJson)) {
            return null;
        }
        List<Booking> books = QueryUtils.extractBooks(bookingJson);
        return books;


    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Booking[] books = new Booking[adapter.getCount()];
        for (int i = 0; i < books.length; i++) {
            books[i] = adapter.getItem(i);
        }
        outState.putParcelableArray("Books Result", (Parcelable[]) books);
    }

}
