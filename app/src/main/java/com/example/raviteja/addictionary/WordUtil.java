package com.example.raviteja.addictionary;


import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class WordUtil {

    private ArrayList<String> wordList;
    private ArrayList<String> usedWords;

    public WordUtil(Context context) throws IOException {

        wordList = new ArrayList<String>();
        usedWords = new ArrayList<String>();
        initializeWordList(context);
    }

    private void initializeWordList(Context context) throws IOException {
        String word;
        AssetManager assetManager;
        BufferedReader reader;
        String FILENAME = "wordlist.txt";

        assetManager = context.getAssets();
        reader = new BufferedReader(new InputStreamReader(assetManager.open(FILENAME)));
        while((word = reader.readLine()) != null)
            wordList.add(word.trim());
        reader.close();
    }

    // must provide a lowercase word ONLY
    public boolean isValidWord(String word) {
        if(word.length() != 4)
            return false;
        else if(usedWords.contains(word))
            return false;
        else {
            int index = Collections.binarySearch(wordList, word);
            if(index >= 0 && index < wordList.size())
                return wordList.get(index).equals(word);
            else
                return false;
        }
    }

    public String suggestWord(String rootWord) {
        usedWords.add(rootWord);
        char[] letters = rootWord.toCharArray();
        for(int i=0; i<4; i++) {
            char c = 'a';
            for(int j=0;j<25;j++) {
                if(c+j != rootWord.charAt(i)) {
                    letters[i] = (char)(c+j);
                    String newWord = new String(letters);
                    if(isValidWord(newWord)) {
                        usedWords.add(newWord);
                        return newWord;
                    }
                }
            }
            letters[i] = rootWord.charAt(i);
        }
        return null;
    }

    public static int changeCount(String str1, String str2) {
        if(str1.length() != str2.length()) return -1;
        else {
            int count = 0;
            for(int i=0; i<str1.length(); i++)
                if(str1.charAt(i) != str2.charAt(i))
                    count++;
            return count;
        }
    }
}