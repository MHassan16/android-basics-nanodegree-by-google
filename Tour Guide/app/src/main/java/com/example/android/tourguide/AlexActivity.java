package com.example.android.tourguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Mostafa on 10/29/2017.
 */

public class AlexActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_list);

        final ArrayList<city> cityAttractions = new ArrayList<city>();
        cityAttractions.add(new city(getResources().getString(R.string.Citadel),getResources().getString(R.string.Citadeladdress),getResources().getString(R.string.CitadelDescription),R.drawable.citadel));
        cityAttractions.add(new city(getResources().getString(R.string.MPalace),getResources().getString(R.string.MPalaceaddress),getResources().getString(R.string.MPalaceDescription),R.drawable.motanzapalace));
        cityAttractions.add(new city(getResources().getString(R.string.Zoo),getResources().getString(R.string.Zooaddress),getResources().getString(R.string.ZoorDescription),R.drawable.alex_zoo));

        cityAdapter adapter = new cityAdapter(this,cityAttractions);
        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);


    }
}
