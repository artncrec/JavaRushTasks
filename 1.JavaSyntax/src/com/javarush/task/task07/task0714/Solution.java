package com.javarush.task.task07.task0714;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/* 
Слова в обратном порядке
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        for (int z = 0; z < 5; z++)
            list.add(z, r.readLine());
        list.remove(2);

        for (int z = list.size() - 1; z >= 0; z--)
            System.out.println(list.get(z));
    }
}


