package com.example.android.tourguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Mostafa on 10/29/2017.
 */

public class GizaActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_list);

        final ArrayList<city> cityAttractions = new ArrayList<city>();
        cityAttractions.add(new city(getResources().getString(R.string.Sphinx),getResources().getString(R.string.Sphinxaddress),getResources().getString(R.string.SphinxDescription),R.drawable.sphinx));
        cityAttractions.add(new city(getResources().getString(R.string.pyramids),getResources().getString(R.string.pyramidsaddress),getResources().getString(R.string.pyramidsDescription),R.drawable.pyramids));

        cityAdapter adapter = new cityAdapter(this,cityAttractions);
        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);


    }
}
