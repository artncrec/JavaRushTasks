package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;

public class Solution {
    public static void main(String[] args) {
        try {
            FileInputStream inputStream = new FileInputStream(args[0]);
            byte rd;
            int allSimbols = 0, probel = 0;
            while (inputStream.available() > 0) {
                rd = (byte) inputStream.read();
                allSimbols++;
                if (rd == 32) probel++;
            }
            inputStream.close();
            double d = Math.round((double) probel / allSimbols * 10000);
            System.out.println(d / 100);
        } catch (Exception e) {
        }
    }
}
