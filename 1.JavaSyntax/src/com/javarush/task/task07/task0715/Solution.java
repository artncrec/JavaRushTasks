package com.javarush.task.task07.task0715;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/* 
Продолжаем мыть раму
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "мама", "мыла", "раму");
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        for (int z = 0; z < list.size(); ) {
            list.add(z + 1, "именно");
            z += 2;
        }
        for (int z = 0; z < list.size(); z++)
            System.out.println(list.get(z));
    }
}
