package com.javarush.task.task32.task3201;

import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws Exception {
        int number = Integer.parseInt(args[1]);
        String text = args[2];
        RandomAccessFile randomAccessFile = new RandomAccessFile(args[0], "rw");
        if (randomAccessFile.length() >= number)
            randomAccessFile.seek(number);
        else randomAccessFile.seek(randomAccessFile.length());
        randomAccessFile.write(text.getBytes());
    }
}