package com.example.raviteja.addictionary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ModeSelectionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_selection);
    }

    public void startGameEasyMode(View view) {
        startGame(GameMode.EASY);
    }

    public void startGameHardMode(View view) {
        startGame(GameMode.HARD);
    }

    public void startGame(GameMode mode) {
        Intent intent = new Intent(ModeSelectionActivity.this, SinglePlayer.class);
        int difficulty = 0;
        switch(mode) {
            case EASY:
                difficulty = 0;
                break;
            case HARD:
                difficulty = 1;
                break;
            case TIMED:
                difficulty = 2;
                break;
        }
        intent.putExtra("difficulty", difficulty);
        this.startActivity(intent);
    }
}
