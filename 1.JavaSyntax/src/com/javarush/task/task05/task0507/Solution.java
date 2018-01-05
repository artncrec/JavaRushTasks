package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int n=0;
        double sum=0;
        while (!(s=reader.readLine()).equals("-1"))
        {
            sum+=Integer.parseInt(s); n++;
        }
        System.out.println(sum/n);
    }
}

