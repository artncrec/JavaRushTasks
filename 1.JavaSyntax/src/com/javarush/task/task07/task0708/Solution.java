package com.javarush.task.task07.task0708;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Самая длинная строка
*/

public class Solution {
    private static List<String> strings;

    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        for (int s = 0; s < 5; s++)
            list.add(r.readLine());

        int max = list.get(0).length();
        for (int s = 0; s < 5; s++) {
            if (list.get(s).length() > max)
                max = list.get(s).length();
        }
        for (int z = 0; z < 5; z++) {
            if (list.get(z).length() == max)
                System.out.println(list.get(z));
        }
    }
}
