package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception
    {
        FileReader reader = new FileReader(args[0]);
        FileWriter outputStream = new FileWriter(args[1]);
        int s;
        boolean wr;
        ArrayList<Character> list = new ArrayList<>();
        while (true)
        {
            while ((s = reader.read()) != ' ' && s != '\r' && s != '\n' && s != -1)
                list.add((char) s);
            if (s == -1)
                break;
            wr = false;
            for (char c : list)
            {
                if (c >= 48 && c < 58)
                {
                    wr = true;
                    break;
                }
            }
            if (wr)
            {
                for (char b : list)
                    outputStream.write((byte) b);
                outputStream.write(32);
            }
            list.clear();
        }
        reader.close();
        outputStream.close();
    }
}
