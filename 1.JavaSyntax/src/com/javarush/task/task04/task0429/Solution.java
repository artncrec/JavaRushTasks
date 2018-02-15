package com.javarush.task.task04.task0429;

/* 
Положительные и отрицательные числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(rd.readLine());
        int b = Integer.parseInt(rd.readLine());
        int c = Integer.parseInt(rd.readLine());
        int p = 0, o = 0;
        if (a > 0) p = p + 1;
        else if (a < 0) o = o + 1;
        if (b > 0) p = p + 1;
        else if (b < 0) o = o + 1;
        if (c > 0) p = p + 1;
        else if (c < 0) o = o + 1;
        System.out.println("количество отрицательных чисел: " + o);
        System.out.println("количество положительных чисел: " + p);
    }
}
