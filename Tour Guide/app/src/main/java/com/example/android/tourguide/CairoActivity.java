package com.example.android.tourguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Mostafa on 10/29/2017.
 */

public class CairoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_list);

        final ArrayList<city> cityAttractions = new ArrayList<city>();
            cityAttractions.add(new city(getResources().getString(R.string.Tower),getResources().getString(R.string.Toweraddress),getResources().getString(R.string.TowerDescription),R.drawable.ic_launcher));
            cityAttractions.add(new city(getResources().getString(R.string.Tahrir),getResources().getString(R.string.Tahriraddress),getResources().getString(R.string.TahrirDescription),R.drawable.tahrir_square));
            cityAttractions.add(new city(getResources().getString(R.string.Palace),getResources().getString(R.string.Palaceaddress),getResources().getString(R.string.PalaceDescription),R.drawable.abden_palace));
            cityAttractions.add(new city(getResources().getString(R.string.Opera),getResources().getString(R.string.Operaaddress),getResources().getString(R.string.OperaDescription),R.drawable.cairo_opera_house));

            cityAdapter adapter = new cityAdapter(this,cityAttractions);
        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);


    }


}
