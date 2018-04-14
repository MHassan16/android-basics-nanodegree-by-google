package com.example.android.musicalstructure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class playerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Button settings = (Button) findViewById(R.id.Setting_Button);
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent settingsIntent = new Intent(playerActivity.this, settingsActivity.class);
                startActivity(settingsIntent);
            }

        });

        Button playList = (Button) findViewById(R.id.PlayList_Button);
        playList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent playListIntent = new Intent(playerActivity.this, playListActivity.class);
                startActivity(playListIntent);
            }

        });

        Button payment = (Button) findViewById(R.id.Payemnt_Button);
        payment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent paymentIntent = new Intent(playerActivity.this, PaymentActivity.class);
                startActivity(paymentIntent);
            }
        });

    }
}