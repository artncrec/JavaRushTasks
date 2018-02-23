package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            FileInputStream inputStream = new FileInputStream(reader.readLine());
            FileOutputStream outputStream = new FileOutputStream(reader.readLine());
            reader.close();
            if (inputStream.available() > 0) {
                byte[] buffer = new byte[inputStream.available()];
                int count = inputStream.read(buffer);
                for (int i = count - 1; i >= 0; i--)
                    outputStream.write(buffer[i]);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
        }
    }
}
