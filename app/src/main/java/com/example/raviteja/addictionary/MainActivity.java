package com.example.raviteja.addictionary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    boolean sound = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSinglePlayer(View view) {
        navigateTo(ModeSelectionActivity.class);
    }

    public void startMultiplayer(View view) {
        Toast.makeText(this, "Multiplayer not yet functional", Toast.LENGTH_SHORT).show();
    }

    public void displayUserWords(View view) {
        Toast.makeText(this, "User Words not yet functional", Toast.LENGTH_SHORT).show();
    }

    public void settings(View view) {
        Toast.makeText(this, "Settings not yet functional", Toast.LENGTH_SHORT).show();
    }

    public void sound(View view) {
        ImageView img = (ImageView) findViewById(R.id.sound);
        if(sound) {
            sound = false;
            img.setImageResource(R.drawable.ic_volume_off_black_24dp);
        } else if(!sound) {
            sound = true;
            img.setImageResource(R.drawable.ic_volume_up_black_24dp);
        }
        //Toast.makeText(this, "Sounds not yet functional", Toast.LENGTH_SHORT).show();
    }

    private void navigateTo(Class c) {
        startActivity(new Intent(this, c));
    }
}
