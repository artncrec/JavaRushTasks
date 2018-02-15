package com.javarush.task.task08.task0827;

import java.util.Date;

/* 
Работа с датой
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(isDateOdd("MAY 1 2013"));
    }

    public static boolean isDateOdd(String date) {
        Date start = new Date();
        Date t = new Date(date);
        start.setHours(0);
        start.setMinutes(0);
        start.setSeconds(0);
        start.setMonth(0);
        start.setDate(1);
        start.setYear(t.getYear());
        return ((t.getTime() - start.getTime()) / (24 * 60 * 60 * 1000)) % 2 != 0;
    }
}
