package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush-лучший сервис обучения Java.   "));
    }

    public static String getPartOfString(String string) {
        if (string == null || string.split(" ").length <= 4)
            throw new TooShortStringException();
        else {
            String[] str = string.split(" ");
            return str[1] + " " + str[2] + " " + str[3] + " " + str[4];
        }
    }

    public static class TooShortStringException extends RuntimeException{
    }
}
