package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            FileInputStream stream = new FileInputStream(reader.readLine());
            reader.close();
            count = 0;
            while (stream.available() > 0) {
                if (stream.read() == 44)
                    count++;
            }
            stream.close();
        } catch (IOException e) {
        }
        System.out.println(count);
    }
}
