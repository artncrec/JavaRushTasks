package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        StringBuilder stringBuilder = new StringBuilder();
        int k = 1;
        if (a>b)
            k = -1;
        for (int i = a;; i+=k) {
            stringBuilder.append(i);
            if (i!=b)
            {
                stringBuilder.append(" ");
            }
            else break;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt() % 1000;
        numberB = random.nextInt() % 10000;
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}