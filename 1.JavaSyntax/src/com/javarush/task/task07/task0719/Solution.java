package com.javarush.task.task07.task0719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Вывести числа в обратном порядке
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();
        for (int s = 0; s < 10; s++)
            list.add(s, Integer.parseInt(reader.readLine()));
        for (int s = list.size() - 1; s >= 0; s--)
            System.out.println(list.get(s));
    }
}
