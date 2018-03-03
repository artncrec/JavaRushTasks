package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException{
        String fileIn = args[1];
        String fileOut = args[2];
        if (args[0].equals("-e")) {
            //encrypt
            FileInputStream inputStream = new FileInputStream(fileIn);
            FileOutputStream outputStream = new FileOutputStream(fileOut);
            while (inputStream.available() > 0)
                outputStream.write(inputStream.read() + 1);
            outputStream.close();
            inputStream.close();
        }
        else if (args[0].equals("-d")) {
            //decrypt
            FileInputStream inputStream = new FileInputStream(fileIn);
            FileOutputStream outputStream = new FileOutputStream(fileOut);
            while (inputStream.available() > 0)
                outputStream.write(inputStream.read() - 1);
            outputStream.close();
            inputStream.close();
        }
    }
}
