package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Mostafa on 12/10/2017.
 */
public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, List<News> newses) {
        super(context, 0, newses);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        News currNews = getItem(position);
        TextView title = (TextView) listItemView.findViewById(R.id.news_title);
        title.setText(currNews.getmTitle());
        TextView date = (TextView) listItemView.findViewById(R.id.news_date);
        date.setText(currNews.getmDate());
        TextView section = (TextView) listItemView.findViewById(R.id.news_section);
        section.setText(currNews.getmSection());
        TextView author = (TextView) listItemView.findViewById(R.id.author);
        author.setText(currNews.getmAuthour());


        return listItemView;
    }

}

