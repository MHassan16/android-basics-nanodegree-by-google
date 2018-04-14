package com.example.android.musicalstructure;

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

        TextView player = (TextView) findViewById(R.id.player_text);
        player.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent playerIntent = new Intent(MainActivity.this, playerActivity.class);
                startActivity(playerIntent);

            }
        });

        TextView playList = (TextView) findViewById(R.id.playlist_text);
        playList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent playListIntent = new Intent(MainActivity.this, playListActivity.class);
                startActivity(playListIntent);

            }
        });

        TextView settings = (TextView) findViewById(R.id.Settings_text);
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent settingsIntent = new Intent(MainActivity.this, settingsActivity.class);
                startActivity(settingsIntent);

            }
        });
    }
}