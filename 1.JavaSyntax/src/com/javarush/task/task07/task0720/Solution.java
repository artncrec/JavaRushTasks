package com.javarush.task.task07.task0720;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Перестановочка подоспела
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());
        ArrayList<String> list = new ArrayList<>();
        for (int z = 0; z < n; z++)
            list.add(z, reader.readLine());
        String t;
        for (int z = 0; z < m; z++) {
            t = list.get(0);
            for (int s = 0; s < n - 1; s++)
                list.set(s, list.get(s + 1));
            list.set(n - 1, t);
        }
        for (int z = 0; z < list.size(); z++)
            System.out.println(list.get(z));
    }
}
