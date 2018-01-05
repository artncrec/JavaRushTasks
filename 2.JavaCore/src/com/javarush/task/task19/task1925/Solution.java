package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        FileWriter outputStream = new FileWriter(args[1]);
        ArrayList<String> list = new ArrayList<>();
        boolean first = true;
        while (reader.ready())
        {
            String[] p = reader.readLine().split(" ");
            for (int i = 0; i < p.length; i++)
            {
                if (p[i].length() > 6)
                {
                    if (!first)
                        outputStream.write(",");
                    first = false;
                    outputStream.write(p[i]);
                }
            }
        }
        reader.close();
        outputStream.close();
    }
}
