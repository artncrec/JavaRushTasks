package com.javarush.task.task09.task0921;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Метод в try..catch
*/

public class Solution {
    public static void main(String[] args) {
        readData();
    }

    public static void readData() {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> array = new ArrayList<>();
        try {
            int i = 0;
            while (true) {
                array.add(Integer.parseInt(r.readLine()));
                i++;
            }
        } catch (Exception e) {
            for (int s : array) {
                System.out.println(s);
            }
        }
    }
}
