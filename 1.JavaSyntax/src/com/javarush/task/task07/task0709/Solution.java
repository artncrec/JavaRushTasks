package com.javarush.task.task07.task0709;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Выражаемся покороче
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        for (int z = 0; z < 5; z++)
            list.add(r.readLine());
        int min = list.get(0).length();
        for (int z = 0; z < 5; z++)
            if (list.get(z).length() < min)
                min = list.get(z).length();
        for (int z = 0; z < 5; z++)
            if (list.get(z).length() == min)
                System.out.println(list.get(z));
    }
}
