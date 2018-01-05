package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader(reader1.readLine()));
        ArrayList<String> data = new ArrayList<>();
        String s;
        while ((s = reader.readLine()) != null)
        {
            data.add(s.replace("\uFEFF", ""));
        }
        reader.close();
        reader1.close();
        for (String z:data)
        {
            int count = 0;
            for (int i = 0; i < words.size(); i++)
            {
                if (z.contains(words.get(i)))
                    count++;
            }
            if (count == 2)
                System.out.println(z);
        }
    }
}
