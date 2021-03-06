package com.example.android.musicalstructure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class settingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button player = (Button) findViewById(R.id.Player_Button);
        player.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent playerIntent = new Intent(settingsActivity.this, playerActivity.class);
                startActivity(playerIntent);
            }

        });

        Button playList = (Button) findViewById(R.id.PlayList_Button);
        playList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent playListIntent = new Intent(settingsActivity.this, playListActivity.class);
                startActivity(playListIntent);
            }

        });
    }
}
