package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();
        FileReader fileReader = new FileReader(file1);
        FileWriter fileWriter = new FileWriter(file2);
        StringBuilder builder = new StringBuilder();
        while (fileReader.ready()) {
            char c = (char) fileReader.read();
            if (c != '\n')
                builder.append(c);
        }
        fileReader.close();
        String s = builder.toString();
        Pattern pattern = Pattern.compile("[^\\p{Punct}]");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find())
            fileWriter.write(s.substring(matcher.start(), matcher.end()));
        fileWriter.close();
    }
}
