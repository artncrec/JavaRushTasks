package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String file1, file2;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        file1 = reader.readLine();
        file2 = reader.readLine();
        reader.close();
        FileInputStream inputStream = new FileInputStream(file1);
        FileOutputStream outputStream = new FileOutputStream(file2);
        while (inputStream.available() > 0) {
            for (int i = 1; i < inputStream.available() + i; i++) {
                if (i % 2 == 0)
                    outputStream.write(inputStream.read());
                else inputStream.read();
            }
        }
        outputStream.close();
        inputStream.close();
    }
}
