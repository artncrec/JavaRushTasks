package com.javarush.task.task04.task0423;

/* 
Фейс-контроль
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String name = rd.readLine();
        int age = Integer.parseInt(rd.readLine());
        if (age > 20)
            System.out.println("И 18-ти достаточно");
    }
}
