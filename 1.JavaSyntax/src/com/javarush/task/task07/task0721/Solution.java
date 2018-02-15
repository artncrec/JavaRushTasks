package com.javarush.task.task07.task0721;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Минимаксы в массивах
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();
        for (int z = 0; z < 20; z++)
            list.add(z, Integer.parseInt(reader.readLine()));

        int maximum = list.get(0);
        int minimum = list.get(0);

        for (int z = 0; z < 20; z++) {
            if (list.get(z) > maximum)
                maximum = list.get(z);
            if (list.get(z) < minimum)
                minimum = list.get(z);
        }

        System.out.println(maximum);
        System.out.println(minimum);
    }
}
