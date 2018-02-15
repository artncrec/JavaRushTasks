package com.javarush.task.task07.task0717;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Удваиваем слова
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader e = new BufferedReader(new InputStreamReader(System.in));
        for (int z = 0; z < 10; z++)
            list.add(z, e.readLine());

        ArrayList<String> result = doubleValues(list);

        for (String z : result)
            System.out.println(z);
    }

    public static ArrayList<String> doubleValues(ArrayList<String> list) {
        for (int z = 0; z < list.size(); z++) {
            list.add(z, list.get(z));
            z++;
        }
        return list;
    }
}
