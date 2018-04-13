package com.example.android.newsapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private NewsAdapter mAdapter;
    public static final String LOG_TAG = MainActivity.class.getName();
    private static final int News_LOADER_ID = 1;
    private TextView mEmptyTextView;
    private String url="https://content.guardianapis.com/search?api-key=07e80410-fc9e-4b0e-9784-686873041f97";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView newsList = (ListView) findViewById(R.id.list);
        mEmptyTextView = (TextView) findViewById(R.id.empty_view);
        mAdapter = new NewsAdapter(this, new ArrayList<News>());
        newsList.setAdapter(mAdapter);
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News currNews = mAdapter.getItem(i);
                Uri newsUri = Uri.parse(currNews.getmUrl());
                Intent newsIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(newsIntent);
            }
        });

        LoaderManager loaderManager;
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            mEmptyTextView.setVisibility(View.GONE);
            loaderManager = getLoaderManager();
            loaderManager.initLoader(News_LOADER_ID, null, this);
        } else {
            View LoadingInicator = findViewById(R.id.loading_indicator);
            LoadingInicator.setVisibility(View.GONE);
            mEmptyTextView.setText(R.string.no_internet_connection);

        }
    }

        public Loader<List<News>> onCreateLoader(int i, Bundle bundle){
            return new NewsLoader(this,url);

        }

        public void onLoadFinished(Loader<List<News>> loader,List<News> data){

            View LoadingIndicator = findViewById(R.id.loading_indicator);
            LoadingIndicator.setVisibility(View.GONE);
            mEmptyTextView.setText(R.string.no_data);
            mAdapter.clear();
            if(data!=null && !data.isEmpty()){
                mAdapter.addAll(data);

            }else{
                mEmptyTextView.setVisibility(View.VISIBLE);
            }

        }
        public void onLoaderReset(Loader<List<News>> loader){
            mAdapter.clear();
        }





}
