package com.javarush.task.task13.task1319;

import java.io.*;
import java.util.ArrayList;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter file = new BufferedWriter(new FileWriter(reader.readLine()));
        String s;
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            s = reader.readLine();
            if (s.equals("exit")) {
                list.add(s);
                break;
            } else list.add(s);
        }
        reader.close();
        for (String line : list) {
            file.write(line);
            file.newLine();
        }
        file.close();
    }
}
