package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream stream = new FileInputStream(reader.readLine());
        reader.close();
        List<Integer> list = new ArrayList<>();
        if (stream.available() > 0) {
            int data;
            while (stream.available() > 0) {
                data = stream.read();
                if (!list.contains(data))
                    list.add(data);
            }
        }
        stream.close();
        for (int i = 0; i < list.size(); i++)
            for (int j = i; j < list.size(); j++)
                if (list.get(j) < list.get(i)) {
                    int z = list.get(j);
                    list.set(j, list.get(i));
                    list.set(i, z);
                }
        for (int i : list)
            System.out.print(i + " ");
    }
}
