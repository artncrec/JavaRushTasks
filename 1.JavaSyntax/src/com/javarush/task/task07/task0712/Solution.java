package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int min = 0, max = 0, minindex = 0, maxindex = 0;
        for (int i = 0; i < 10; i++) {
            list.add(i, reader.readLine());
            if (i == 0) min = list.get(i).length();
            if (list.get(i).length() > max) {
                max = list.get(i).length();
                maxindex = i;
            }
            if (list.get(i).length() < min) {
                min = list.get(i).length();
                minindex = i;
            }
        }
        System.out.println(maxindex < minindex ? list.get(maxindex) : list.get(minindex));
    }
}
