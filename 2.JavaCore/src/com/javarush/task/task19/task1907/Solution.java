package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileReader fileReader = new FileReader(reader.readLine());
        reader.close();
        StringBuilder builder = new StringBuilder();
        while (fileReader.ready()) {
            builder.append((char) fileReader.read());
        }
        fileReader.close();
        int count = 0;
        Pattern pattern = Pattern.compile("\\bworld\\b");
        Matcher matcher = pattern.matcher(builder.toString());
        while (matcher.find())
            count++;
        System.out.println(count);
    }
}
