package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args) {
        try {
            char[] number = args[0].toUpperCase().toCharArray();
            if (number.length > 255)
                System.out.println("incorrect");
            else {
                int max = -1;
                for (int i = 0; i < number.length; i++) {
                    if (number[i] >= 48 && number[i] <= 90) {
                        if (number[i] >= 58 && number[i] <= 64) {
                            max = -1;
                            break;
                        } else if (max < number[i])
                            max = number[i];
                    } else {
                        max = -1;
                        break;
                    }
                }
                if (max > -1) {
                    if (max <= 57)
                        max = max - 48;
                    else max = max - 55;
                    if (max == 0) max = 1;
                    System.out.println(max + 1);
                } else
                    System.out.println("incorrect");
            }
        } catch (Exception e) {
        }
    }
}