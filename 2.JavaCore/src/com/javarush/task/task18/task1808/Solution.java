package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String file1 = reader.readLine();
            String file2 = reader.readLine();
            String file3 = reader.readLine();
            reader.close();
            FileInputStream stream = new FileInputStream(file1);
            FileOutputStream outputStream = new FileOutputStream(file2);
            FileOutputStream outputStream1 = new FileOutputStream(file3);
            if (stream.available() > 0) {
                byte[] buffer = new byte[stream.available()];
                int count = stream.read(buffer);
                if (count % 2 == 0) {
                    outputStream.write(buffer, 0, count / 2);
                    outputStream1.write(buffer, count / 2, count / 2);
                }
                else {
                    outputStream.write(buffer, 0, count / 2 + 1);
                    outputStream1.write(buffer, count / 2 + 1, count / 2);
                }
            }
            stream.close();
            outputStream.close();
            outputStream1.close();
        } catch (IOException e) {
        }
    }
}
