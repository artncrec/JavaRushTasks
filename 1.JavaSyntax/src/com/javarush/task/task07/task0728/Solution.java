package com.javarush.task.task07.task0728;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
В убывающем порядке
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] array = new int[20];
        for (int i = 0; i < 20; i++) {
            array[i] = Integer.parseInt(reader.readLine());
        }

        sort(array);

        for (int x : array) {
            System.out.println(x);
        }
    }

    public static void sort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int val = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] < val) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = val;
        }
    }
}
