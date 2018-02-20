package com.javarush.task.task17.task1721;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        String file1, file2, s;
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(is);
        try {
            file1 = reader.readLine();
            file2 = reader.readLine();
            is.close();
            reader = new BufferedReader(new FileReader(file1));
            while ((s = reader.readLine()) != null)
                allLines.add(s);
            reader = new BufferedReader(new FileReader(file2));
            while ((s = reader.readLine()) != null)
                forRemoveLines.add(s);
            reader.close();
        } catch (IOException e) {
        }
        Solution sol = new Solution();
        try {
            sol.joinData();
        } catch (CorruptedDataException e) {
        }
    }

    public void joinData() throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines))
            allLines.removeAll(forRemoveLines);
        else {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}
