package com.example.android.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mostafa on 12/10/2017.
 */

public class QueryUtils {

    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils() {

    }

    private static List<News> extractResultFromJson(String newJSON) {
        if (TextUtils.isEmpty(newJSON)) {
            return null;
        }


        List<News> newses = new ArrayList<>();

        try {
                JSONObject baseJson = new JSONObject(newJSON);
            JSONObject resposne = baseJson.getJSONObject("response");
            JSONArray results = resposne.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject news = results.getJSONObject(i);
                String title = news.getString("webTitle");
                String date = news.getString("webPublicationDate");
                date = formatDate(date);
                String section = news.getString("sectionName");
                String url = news.getString("webUrl");
                String author = "";

                JSONArray tagsArray= news.getJSONArray("tags");
                for(int j=0;j<tagsArray.length();j++){
                    JSONObject tagObject=tagsArray.getJSONObject(j);
                    String firstname=tagObject.optString("firstName");
                    author = firstname;

                }
                News n = new News(section, date,author, title, url);
                newses.add(n);


            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the news JSON results", e);
        }
        return newses;


    }

    public static List<News> FetchNewsData(String requstUrl) {
        try {
            Thread.sleep(2000);

        } catch (InterruptedException i) {
            i.printStackTrace();
        }
        URL url = createUrl(requstUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        List<News> news = extractResultFromJson(jsonResponse);
        return news;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException m) {
            Log.e(LOG_TAG, "Problem building the URL", m);
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
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the news JSON results.", e);
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
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    private static String formatDate(String data) {
        String jsonDate = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat jsonFomat = new SimpleDateFormat(jsonDate, Locale.ENGLISH);
        try {
            Date parsedJsonDate = jsonFomat.parse(data);
            String finalDate = "MMM d, yyy";
            SimpleDateFormat finalDateFormat = new SimpleDateFormat(finalDate, Locale.ENGLISH);
            return finalDateFormat.format(parsedJsonDate);
        } catch (ParseException p) {
            Log.e("QueryUtils", "Error parsing JSON date: ", p);
            return "";
        }

    }


}
