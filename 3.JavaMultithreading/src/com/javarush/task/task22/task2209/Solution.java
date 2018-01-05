package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
        while ((string = reader.readLine()) != null)
        {
            for (String s : string.split(" "))
            {
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
        if (words == null)
            return builder.append("");
        String temp;
        for (int i = 0; i < words.length; i++) {
            for (int j = i; j < words.length; j++) {
                if (words[j].compareTo(words[i]) < 0) {
                    temp = words[i];
                    words[i] = words[j];
                    words[j] = temp;
                }
            }
        }
        boolean isAdded;
        for (int i = 0, k = 0; k < words.length; k++) {
            if (k == words.length - 1 && builder.length() > 0) {
                builder.append(" ");
                builder.append(words[i]);
                words[i] = null;
                break;
            }
            int step = 0;
            isAdded = false;
            if (words[i].toLowerCase().charAt(0) < words[i].charAt(words[i].length() - 1))
                step = 1;
            else
                step = -1;
            for (int j = i + step; j < words.length && j >= 0; j += step) {
                if (words[j] == null) continue;
                if (words[i].charAt(words[i].length() - 1) == words[j].toLowerCase().charAt(0)) {
                    if (builder.length() > 0) builder.append(" ");
                    builder.append(words[i]);
                    words[i] = null;
                    i = j;
                    isAdded = true;
                    break;
                }
                if (builder.length() > 0 && builder.charAt(0) == words[j].charAt(words[j].length() - 1)) {
                    builder.insert(0, words[j] + " ");
                    words[j] = null;
                }
            }
            if (!isAdded) {
                builder.append(" ");
                builder.append(words[i]);
                words[i] = null;
                break;
            }
        }
        return builder;
    }
}
