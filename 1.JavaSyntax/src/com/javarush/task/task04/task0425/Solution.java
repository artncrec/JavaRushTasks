package com.javarush.task.task04.task0425;

/* 
Цель установлена!
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(rd.readLine());
        int b = Integer.parseInt(rd.readLine());
        if (a < 0) {
            if (b < 0)
                System.out.println(3);
            else
                System.out.println(2);
        } else if (b < 0)
            System.out.println(4);
        else System.out.println(1);
    }
}
