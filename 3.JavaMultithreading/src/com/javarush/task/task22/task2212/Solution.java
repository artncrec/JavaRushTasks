package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber ==null) return false;
        if (telNumber.matches("\\d{10}")) return true;
        if (telNumber.matches("\\.{11}|\\d+\\-\\d+")) return true;
        if (telNumber.matches("\\.{12}|\\d+\\-\\d+\\-\\d+")) return true;
        if (telNumber.matches("\\.{14}|\\+\\d{2}\\d+\\-\\d+")) return true;
        if (telNumber.matches("\\.{15}|\\+\\d{2}\\d+\\-\\d+\\-\\d+")) return true;
        if (telNumber.matches("\\+\\d{12}")) return true;
        if (telNumber.matches("\\+\\d{2}\\(\\d{3}\\)\\d{7}")) return true;
        if (telNumber.matches("\\.{16}|\\+\\d{2}\\(\\d{3}\\)\\d+\\-\\d+")) return true;
        if (telNumber.matches("\\.{17}|\\+\\d{2}\\(\\d{3}\\)\\d+\\-\\d+\\-\\d+")) return true;
        if (telNumber.matches("\\(\\d{3}\\)\\d{7}")) return true;
        if (telNumber.matches("\\.{13}|\\(\\d{3}\\)\\d+\\-\\d+")) return true;
        if (telNumber.matches("\\.{14}|\\(\\d{3}\\)\\d+\\-\\d+\\-\\d+")) return true;

        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkTelNumber("+380501234567"));
        System.out.println(checkTelNumber("+38(050)1234567"));
        System.out.println(checkTelNumber("+38050123-45-67"));
        System.out.println(checkTelNumber("050123-4567"));
        System.out.println(checkTelNumber("+38)050(1234567"));
        System.out.println(checkTelNumber("+38(050)1-23-45-6-7"));
        System.out.println(checkTelNumber("050ххх4567"));
        System.out.println(checkTelNumber("050123456"));
        System.out.println(checkTelNumber("(977)424-38-07"));
    }
}
