package com.example.raviteja.addictionary;


import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class WordUtil {

    private ArrayList<String> wordList;
    private ArrayList<String> usedWords;
    private GameMode difficultyMode;

    WordUtil(Context context, GameMode gameMode) throws IOException {

        wordList = new ArrayList<>();
        usedWords = new ArrayList<>();
        this.difficultyMode = gameMode;
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
    int isValidWord(String word) {
        if(word.length() != 4)
            return 0;
        else if(usedWords.contains(word))
            return -1; // USED WORD
        else {
            int index = Collections.binarySearch(wordList, word);
            if(index >= 0 && index < wordList.size())
                if(wordList.get(index).equals(word))
                    return 1; // TRUE
        }
        return 0; // NOT A VALID WORD
    }

    String suggestWord(String rootWord) {
        if(this.difficultyMode == GameMode.HARD)
                return suggestWordHard(rootWord);
        else
            return suggestWordEasy(rootWord);
    }

    private String suggestWordEasy(String rootWord) {
        usedWords.add(rootWord);
        char[] letters = rootWord.toCharArray();
        for(int i=0; i<4; i++) {
            char c = 'a';
            for(int j=0;j<25;j++) {
                if(c+j != rootWord.charAt(i)) {
                    letters[i] = (char)(c+j);
                    String newWord = new String(letters);
                    if(isValidWord(newWord) == 1) {
                        usedWords.add(newWord);
                        return newWord;
                    }
                }
            }
            letters[i] = rootWord.charAt(i);
        }
        return null;
    }

    private String suggestWordHard(String rootWord) {
        usedWords.add(rootWord);
        char[] letters = rootWord.toCharArray();
        HashMap<String, Integer> possibleWords = new HashMap<String, Integer>();
        for(int i=0; i<4; i++) {
            char c = 'a';
            for(int j=0;j<25;j++) {
                if(c+j != rootWord.charAt(i)) {
                    letters[i] = (char)(c+j);
                    String newWord = new String(letters);
                    if(isValidWord(newWord) == 1) {
                        possibleWords.put(newWord, getPossibilityCount(newWord));
                    }
                }
            }
            letters[i] = rootWord.charAt(i);
        }
        if(possibleWords.size() > 0) {
            int min = 1 << 29;
            String newWord = "";
            for (Map.Entry<String, Integer> entry : possibleWords.entrySet()) {
                if (entry.getValue() < min) {
                    min = entry.getValue();
                    newWord = entry.getKey();
                }
            }
            return newWord;
        }
        return null;
    }

    // computes the number of possible words which can be generated using the word provided
    // excluding already used words
    private int getPossibilityCount(String rootWord) {
        int count = 0;
        char[] letters = rootWord.toCharArray();
        for(int i=0; i<4; i++) {
            char c = 'a';
            for(int j=0;j<25;j++) {
                if(c+j != rootWord.charAt(i)) {
                    letters[i] = (char)(c+j);
                    String newWord = new String(letters);
                    if(isValidWord(newWord) == 1) {
                        count++;
                    }
                }
            }
            letters[i] = rootWord.charAt(i);
        }
        return count;
    }

    static int changeCount(String str1, String str2) {
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