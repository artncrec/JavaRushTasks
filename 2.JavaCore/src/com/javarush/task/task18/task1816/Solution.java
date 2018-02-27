package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.FileInputStream;

public class Solution {
    public static void main(String[] args) {
        try {
            FileInputStream inputStream = new FileInputStream(args[0]);
            int count = 0, data;
            while (inputStream.available() > 0) {
                data = inputStream.read();
                if (data >= 65 && data <= 90 || data >= 97 && data <= 122)
                    count++;
            }
            inputStream.close();
            System.out.println(count);
        } catch (Exception e) {
        }
    }
}
