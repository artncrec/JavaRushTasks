package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName1 = reader.readLine();
            String fileName2 = reader.readLine();
            String fileName3 = reader.readLine();
            reader.close();
            FileOutputStream outputStream = new FileOutputStream(fileName1);
            FileInputStream inputStream = new FileInputStream(fileName2);
            while (inputStream.available() > 0)
                outputStream.write(inputStream.read());
            outputStream.flush();
            inputStream = new FileInputStream(fileName3);
            while (inputStream.available() > 0)
                outputStream.write(inputStream.read());
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
        }
    }
}
