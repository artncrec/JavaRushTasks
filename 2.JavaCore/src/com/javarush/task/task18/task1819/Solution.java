package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName1 = reader.readLine();
            String fileName2 = reader.readLine();
            reader.close();
            FileInputStream inputStream = new FileInputStream(fileName1);
            byte[] first = new byte[inputStream.available()];
            inputStream.read(first);
            FileOutputStream outputStream = new FileOutputStream(fileName1);
            inputStream = new FileInputStream(fileName2);
            while (inputStream.available() > 0)
                outputStream.write(inputStream.read());
            outputStream.write(first);
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
        }
    }
}
