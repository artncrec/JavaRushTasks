package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bf = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();
        ArrayList<String> list = new ArrayList<>();
        String line;
        while ((line = bf.readLine()) != null) {
            list.add(line);
        }
        bf.close();
        ArrayList<Integer> integers = new ArrayList<>();
        for (String s : list) {
            int a = Integer.parseInt(s);
            if (a % 2 == 0)
                integers.add(a);
        }
        for (int i = 0; i < integers.size(); i++)
            for (int j = i; j < integers.size(); j++) {
                if (integers.get(j) < integers.get(i)) {
                    int z = integers.get(j);
                    integers.set(j, integers.get(i));
                    integers.set(i, z);
                }
            }
        for (int i : integers)
            System.out.println(i);
    }
}
