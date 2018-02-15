package com.javarush.task.task04.task0426;

/* 
Ярлыки и числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(rd.readLine());
        if (a > 0) {
            if (a % 2 == 0)
                System.out.println("положительное четное число");
            else System.out.println("положительное нечетное число");
        } else if (a == 0) System.out.println("нулевое число");
        else if (a % 2 == 0)
            System.out.println("отрицательное четное число");
        else System.out.println("отрицательное нечетное число");
    }
}