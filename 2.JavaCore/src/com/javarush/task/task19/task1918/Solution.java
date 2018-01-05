package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileReader fileReader = new FileReader(reader.readLine());
        ArrayList<Character> list = new ArrayList<>();
        char c;
        while (fileReader.ready())
        {
            c = (char) fileReader.read();
            if (c!='\n' && c!='\r')
                list.add(c);
        }
        reader.close();
        fileReader.close();
        StringBuilder builder = new StringBuilder();
        for (char d : list)
            builder.append(d);
        String data = builder.toString();
        ArrayList<Integer> start = new ArrayList<>();
        ArrayList<Integer> end = new ArrayList<>();
        String regex = "<"+args[0];
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(data);
        while (m.find())
        {
            start.add(m.start());
        }
        regex = "</"+args[0];
        p = Pattern.compile(regex);
        m = p.matcher(data);
        while (m.find())
        {
            end.add(m.start());
        }
        int[][] ints = new int[2][start.size()+end.size()];
        int count = 0;
        for (int j = 0, i=0; j < end.size(); count++)
        {
            if (i==start.size())
            {
                ints[0][count] = 0;
                ints[1][count] = end.get(j++);
            }
            else
            {
                if (start.get(i) < end.get(j))
                {
                    ints[0][count] = start.get(i++);
                    ints[1][count] = 0;
                }
                else
                    if (start.get(i) > end.get(j))
                    {
                        ints[0][count] = 0;
                        ints[1][count] = end.get(j++);
                    }
            }
        }
        for (int j = 0; j < ints[1].length; j++)
        {
            count = 0;
            for (int i = j; i < ints[1].length; i++)
            {
                if (ints[1][i] == 0)
                {
                    count++;
                }
                else if (ints[0][i] == 0 && count>0)
                {
                    count--;
                    if (count == 0)
                    {
                        System.out.println(data.substring(ints[0][j], ints[1][i]+args[0].length()+3));
                        break;
                    }
                }
                else break;
            }
        }
    }
}
