package com.javarush.task.task14.task1420;

/* 
НОД
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        reader.close();
        if (a < b) {
            a = a + b;
            b = a - b;
            a = a - b;
        }
        for (int i = b; i > 0; i--)
            if (a % i == 0 && b % i == 0) {
                System.out.println(i);
                break;
            }
    }
}
