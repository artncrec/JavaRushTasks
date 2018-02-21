package com.javarush.task.task18.task1801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream stream = new FileInputStream(reader.readLine());
        reader.close();
        int max = 0, data;
        while (stream.available() > 0) {
            if ((data = stream.read()) > max)
                max = data;
        }
        stream.close();
        System.out.println(max);
    }
}
