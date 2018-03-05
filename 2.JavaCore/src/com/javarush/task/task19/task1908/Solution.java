package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();
        FileReader fileReader = new FileReader(file1);
        FileWriter fileWriter = new FileWriter(file2);
        StringBuilder builder = new StringBuilder();
        while (fileReader.ready()) {
            builder.append((char) fileReader.read());
        }
        fileReader.close();
        String[] strings = builder.toString().split(" ");
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher;
        for (String s : strings) {
            matcher = pattern.matcher(s);
            while (matcher.find())
                fileWriter.write(s.substring(matcher.start(), matcher.end()) + " ");
        }
        fileWriter.close();
    }
}
