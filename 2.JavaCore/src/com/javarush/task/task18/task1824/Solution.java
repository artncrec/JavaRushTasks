package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try {
            String fileName = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                try {
                    fileName = reader.readLine();
                    new FileInputStream(fileName);
                } catch (FileNotFoundException e) {
                    System.out.println(fileName);
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
        }
    }
}
