package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* 
Работа с датами
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        String[] strings = date.split(" ");
        if (strings[0].contains(".")) {
            printDateFirst(strings[0], new SimpleDateFormat("dd.M.yyyy"));
            if (strings.length > 1 && strings[1].contains(":"))
                printDateSecond(strings[1], new SimpleDateFormat("hh:mm:ss"));
        }
        else if (strings[0].contains(":")){
            printDateSecond(strings[0], new SimpleDateFormat("hh:mm:ss"));
        }
    }

    private static void printDateFirst(String date, SimpleDateFormat format) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("День: " + calendar.get(Calendar.DATE));
        int i = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println("День недели: " + (i==0?7:i));
        System.out.println("День месяца: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("День года: " + calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println("Неделя месяца: " + calendar.get(Calendar.WEEK_OF_MONTH));
        System.out.println("Неделя года: " + calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println("Месяц: " + (calendar.get(Calendar.MONTH)+1));
        System.out.println("Год: " + calendar.get(Calendar.YEAR));
    }

    private static void printDateSecond(String date, SimpleDateFormat format) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        System.out.println("AM или PM: " + (calendar.get(Calendar.AM_PM)==1?"PM":"AM"));
        System.out.println("Часы: " + calendar.get(Calendar.HOUR));
        System.out.println("Часы дня: " + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("Минуты: " + calendar.get(Calendar.MINUTE));
        System.out.println("Секунды: " + calendar.get(Calendar.SECOND));
    }
}
