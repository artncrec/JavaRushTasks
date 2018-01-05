package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws Exception {
        long number = Integer.parseInt(args[1]);
        String text = args[2];
        RandomAccessFile randomAccessFile = new RandomAccessFile(args[0], "rw");
        if (number + text.length() <= randomAccessFile.length()) {
            byte[] bytes = new byte[text.length()];
            randomAccessFile.seek(number);
            randomAccessFile.read(bytes, 0, text.length());
            randomAccessFile.seek(randomAccessFile.length());
            if (new String(bytes).equals(text))
                randomAccessFile.write("true".getBytes());
            else
                randomAccessFile.write("false".getBytes());
        } else {
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.write("false".getBytes());
        }
    }
}
