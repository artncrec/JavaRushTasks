package com.javarush.task.task04.task0422;

/* 
18+
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String name = rd.readLine();
        int age = Integer.parseInt(rd.readLine());
        if (age < 18)
            System.out.println("Подрасти еще");
    }
}
