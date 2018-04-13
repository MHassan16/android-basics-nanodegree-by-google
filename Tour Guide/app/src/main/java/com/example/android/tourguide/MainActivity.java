package com.example.android.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView cairo = (TextView) findViewById(R.id.Cairo);
        cairo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cairoIntent = new Intent(MainActivity.this, CairoActivity.class);
                startActivity(cairoIntent);

            }
        });
        TextView alex = (TextView) findViewById(R.id.Alex);
        alex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent alexIntent = new Intent(MainActivity.this, AlexActivity.class);
                startActivity(alexIntent);

            }
        });
        TextView giza = (TextView) findViewById(R.id.Giza);
        giza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gizaIntent = new Intent(MainActivity.this, GizaActivity.class);
                startActivity(gizaIntent);

            }
        });
        TextView Luxor = (TextView) findViewById(R.id.Luxor);
        Luxor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent luxorIntent = new Intent(MainActivity.this, LuxorActivity.class);
                startActivity(luxorIntent);

            }
        });

    }
}
