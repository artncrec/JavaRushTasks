package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName1 = reader.readLine();
            reader.close();
            BufferedReader filereader = new BufferedReader(new FileReader(fileName1));
            String s;
            while ((s = filereader.readLine()) != null) {
                if (s.startsWith(args[0] + " ")) {
                    System.out.println(s);
                    break;
                }
            }
            filereader.close();
        } catch (IOException e) {
        }
    }
}
