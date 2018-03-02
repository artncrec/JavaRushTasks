package com.javarush.task.task18.task1820;

/* 
Округление чисел
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
            FileOutputStream outputStream = new FileOutputStream(fileName2);
            StringBuilder builder = new StringBuilder();
            char c;
            while (inputStream.available() > 0) {
                c = (char) inputStream.read();
                if ((c == ' ' || inputStream.available() == 0) && !builder.toString().equals("")) {
                    if (inputStream.available() == 0) builder.append(c);
                    String s = String.valueOf(Math.round(Double.parseDouble(builder.toString())));
                    outputStream.write(s.getBytes());
                    outputStream.write(32);
                    builder.delete(0, builder.length());
                } else builder.append(c);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
        }
    }
}
