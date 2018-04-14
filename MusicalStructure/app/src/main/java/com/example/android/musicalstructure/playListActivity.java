package com.example.android.musicalstructure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class playListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        Button settings = (Button) findViewById(R.id.Setting_Button);
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent settingsIntent = new Intent(playListActivity.this, settingsActivity.class);
                startActivity(settingsIntent);
            }

        });

        Button player = (Button) findViewById(R.id.Player_Button);
        player.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent playerIntent = new Intent(playListActivity.this, playerActivity.class);
                startActivity(playerIntent);
            }

        });
    }
}