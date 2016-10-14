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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSinglePlayer(View view) {
        navigateTo(ModeSelectionActivity.class);
    }

    public void startMultiplayer(View view) {
        Toast.makeText(this, "not yet functional", Toast.LENGTH_SHORT).show();
    }

    public void displayUserWords(View view) {
        Toast.makeText(this, "not yet functional", Toast.LENGTH_SHORT).show();
    }

    private void navigateTo(Class c) {
        startActivity(new Intent(this, c));
    }
}
