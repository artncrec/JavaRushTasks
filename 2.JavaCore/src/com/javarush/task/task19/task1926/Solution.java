package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader0 = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader(reader0.readLine()));
        reader0.close();
        ArrayList<String> list = new ArrayList<>();
        while (reader.ready())
        {
            list.add(reader.readLine().replace("\uFEFF",""));
        }
        reader.close();
        for (String p : list)
        {
            for (int i = p.length()-1; i >= 0; i--)
            {
                System.out.print(p.toCharArray()[i]);
            }
            System.out.println();
        }
    }
}