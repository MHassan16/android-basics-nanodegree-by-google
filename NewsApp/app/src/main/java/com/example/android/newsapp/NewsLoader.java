package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by Mostafa on 12/10/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private static final String LOG_TAG = NewsLoader.class.getName();

    String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    protected void onStartLoading() {
        Log.i(LOG_TAG,"Test: onStartLoading() called ...");
        forceLoad();
    }

    public List<News> loadInBackground() {
        Log.i(LOG_TAG,"Test: loadInBackground() called ...");

        if(mUrl==null)
            return null;

        List<News> newses = QueryUtils.FetchNewsData(mUrl);
        return newses;

    }
}
