package com.example.android.tourguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Mostafa on 10/29/2017.
 */

public class LuxorActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_list);

        final ArrayList<city> cityAttractions = new ArrayList<city>();
        cityAttractions.add(new city(getResources().getString(R.string.Karnak),getResources().getString(R.string.Karnakaddress),getResources().getString(R.string.KarnakDescription),R.drawable.karnak));
        cityAttractions.add(new city(getResources().getString(R.string.ValleyofQueens),getResources().getString(R.string.Valleyaddress),getResources().getString(R.string.valleyDescription),R.drawable.valley));
        cityAttractions.add(new city(getResources().getString(R.string.Museum),getResources().getString(R.string.Musuemaddress),getResources().getString(R.string.MusuemDescription ),R.drawable.musem));

        cityAdapter adapter = new cityAdapter(this,cityAttractions);
        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);


    }
}
