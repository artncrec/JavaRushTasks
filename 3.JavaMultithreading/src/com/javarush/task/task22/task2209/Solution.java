package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader(consoleReader.readLine()));
        consoleReader.close();
        String string;
        List<String> words = new ArrayList<>();
        while ((string = reader.readLine()) != null) {
            for (String s : string.split(" ")) {
                if (s.contains("\uFEFF"))
                    s = new StringBuilder(s).deleteCharAt(0).toString();
                words.add(s);
            }
        }
        reader.close();

        StringBuilder result = getLine(words.toArray(new String[words.size()]));
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder builder = new StringBuilder();
        if (words == null || words.length == 0)
            return builder.append("");

        String[] myWords = Arrays.copyOf(words, words.length);
        Arrays.sort(myWords);
        builder.append(myWords[0]);
        myWords[0] = null;
        for (int i = 0; i < myWords.length; i++) {
            if (myWords[i] == null) continue;
            if (myWords[i].toLowerCase().charAt(0) == builder.toString().toLowerCase().charAt(builder.length() - 1)) {
                builder.append(" ");
                builder.append(myWords[i]);
                myWords[i] = null;
                i = 0;
            }
        }
        for (int i = 0; i < myWords.length; i++) {
            if (myWords[i] == null) continue;
            if (myWords[i].toLowerCase().charAt(myWords[i].length() - 1) == builder.toString().toLowerCase().charAt(0)) {
                builder.insert(0, " ");
                builder.insert(0, myWords[i]);
                myWords[i] = null;
                i = 0;
            }
        }
        for (int i = 0; i < myWords.length; i++) {
            if (myWords[i] == null) continue;
            builder.append(" ");
            builder.append(myWords[i]);
            myWords[i] = null;
        }
        return builder;
    }
}
