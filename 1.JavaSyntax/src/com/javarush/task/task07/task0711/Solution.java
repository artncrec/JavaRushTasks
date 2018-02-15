package com.javarush.task.task07.task0711;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Удалить и вставить
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        for (int z = 0; z < 5; z++)
            list.add(z, r.readLine());
        String s;
        for (int z = 0; z < 13; z++) {
            s = list.remove(list.size() - 1);
            list.add(0, s);
        }
        for (int z = 0; z < 5; z++)
            System.out.println(list.get(z));
    }
}
