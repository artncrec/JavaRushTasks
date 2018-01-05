package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws Exception{
        if (string == null || string.split("\t").length < 3)
            throw new TooShortStringException();
        else
        {
            String s = string.substring(string.indexOf('\t') + 1);
            return s.substring(0, s.indexOf('\t'));
        }
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
