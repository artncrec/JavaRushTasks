package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();
        FileReader fileReader = new FileReader(file1);
        FileWriter fileWriter = new FileWriter(file2);
        char c;
        while (fileReader.ready()) {
            if ((c = (char) fileReader.read()) == '.')
                fileWriter.write('!');
            else fileWriter.write(c);
        }
        fileReader.close();
        fileWriter.close();
    }
}
