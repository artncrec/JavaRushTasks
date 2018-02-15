package com.javarush.task.task05.task0532;

import java.io.*;

/* 
Задача по алгоритмам
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int maximum = 0;
        while (n > 0) {
            int a = Integer.parseInt(reader.readLine());
            if (maximum < a)
                maximum = a;
            n--;
        }
        System.out.println(maximum);
    }
}
