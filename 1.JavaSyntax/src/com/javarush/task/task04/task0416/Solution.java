package com.javarush.task.task04.task0416;

/* 
Переходим дорогу вслепую
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        float t = Float.parseFloat(read.readLine());
        if (t % 5 < 3)
            System.out.println("зеленый");
        else if (t % 5 < 4)
            System.out.println("желтый");
        else
            System.out.println("красный");
    }
}