package com.example.raviteja.addictionary;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SinglePlayer extends AppCompatActivity implements View.OnKeyListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_single_player);

        firstEntry = true;
        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editText);
        listAdapter = new WordListAdapter(this.getApplicationContext());
        initSucess = true;
        try {
            wordUtil = new WordUtil(this.getApplicationContext());
        } catch (IOException ex) {
            Log.e("ADDICTIONARY", ex.toString());
            Toast.makeText(this, "Debug: Cannot initialize wordlist :(", Toast.LENGTH_SHORT).show();
            initSucess = false;
        }

        listView.setAdapter(listAdapter);
        editText.setOnKeyListener(this);
        editText.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

    }


    private String lastWord;
    private boolean firstEntry;
    private ListView listView;
    private EditText editText;
    private WordListAdapter listAdapter;
    private WordUtil wordUtil;
    private boolean initSucess;

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(event.getAction() != KeyEvent.ACTION_DOWN)
            return true;
        if(keyCode == KeyEvent.KEYCODE_ENTER) {
            if(!initSucess) {
                Toast.makeText(this, "Sorry, Initialization failed, please refer log", Toast.LENGTH_SHORT).show();
                return false;
            }
            else {
                String word = editText.getText().toString();
                if (word.trim().length() == 4) {
                    if(!firstEntry) {
                        if (WordUtil.changeCount(lastWord, word) > 1) {
                            Toast.makeText(this, "Invalid entry, make sure you change exactly 1 character from the last word", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }
                    if(wordUtil.isValidWord(word)) {
                        listAdapter.addItem(word);
                        String sysWord = wordUtil.suggestWord(word);
                        listAdapter.addItem(sysWord);
                        lastWord = sysWord;
                        editText.setText(lastWord);
                        editText.setSelection(lastWord.length());
                        if(firstEntry) firstEntry = false;
                        listView.smoothScrollToPosition(listAdapter.getCount()+1);
                    }
                    else {
                        Toast.makeText(this, "'" + word + "' is not a valid word!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "'" + word + "' is not a 4 letter word!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return false;
    }
}
