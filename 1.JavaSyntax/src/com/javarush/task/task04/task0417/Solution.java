package com.javarush.task.task04.task0417;

/* 
Существует ли пара?
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a, b, c;
        a = Integer.parseInt(reader.readLine());
        b = Integer.parseInt(reader.readLine());
        c = Integer.parseInt(reader.readLine());
        if (a == b)
            if (b == c)
                System.out.println(a + " " + b + " " + c);
            else System.out.println(a + " " + b);
        else if (b == c)
            System.out.println(b + " " + c);
        else if (a == c)
            System.out.println(a + " " + c);
    }
}