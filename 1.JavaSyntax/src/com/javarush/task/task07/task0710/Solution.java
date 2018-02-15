package com.javarush.task.task07.task0710;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
В начало списка
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        for (int z = 0; z < 10; z++)
            list.add(0, r.readLine());
        for (int z = 0; z < 10; z++)
            System.out.println(list.get(z));
    }
}
