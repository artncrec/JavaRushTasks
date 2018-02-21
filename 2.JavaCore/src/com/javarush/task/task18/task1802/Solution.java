package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream stream = new FileInputStream(reader.readLine());
        reader.close();
        int data;
        if (stream.available() > 0) {
            int min = stream.read();
            while (stream.available() > 0) {
                if ((data = stream.read()) < min)
                    min = data;
            }
            System.out.println(min);
        }
        stream.close();
    }
}
