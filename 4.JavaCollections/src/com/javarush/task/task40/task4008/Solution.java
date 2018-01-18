package com.javarush.task.task40.task4008;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Locale;

/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        printDate("9.10.2017 5:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        String[] strings = date.split(" ");
        if (strings[0].contains(".")) {
            printDateFirst(strings[0], DateTimeFormatter.ofPattern("d.M.y"));
            if (strings.length > 1 && strings[1].contains(":"))
                printDateSecond(strings[1], DateTimeFormatter.ofPattern("H:m:s"));
        }
        else if (strings[0].contains(":")){
            printDateSecond(strings[0], DateTimeFormatter.ofPattern("H:m:s"));
        }
    }

    private static void printDateFirst(String string, DateTimeFormatter formatter) {
        LocalDate date = LocalDate.parse(string, formatter);
        System.out.println("День: " + date.getDayOfMonth());
        System.out.println("День недели: " + date.getDayOfWeek().getValue());
        System.out.println("День месяца: " + date.getDayOfMonth());
        System.out.println("День года: " + date.getDayOfYear());
        System.out.println("Неделя месяца: " + date.get(WeekFields.of(Locale.getDefault()).weekOfMonth()));
        System.out.println("Неделя года: " + (date.get(WeekFields.of(Locale.getDefault()).weekOfYear())));
        System.out.println("Месяц: " + date.getMonthValue());
        System.out.println("Год: " + date.getYear());
    }

    private static void printDateSecond(String string, DateTimeFormatter formatter) {
        LocalTime localTime = LocalTime.parse(string, formatter);
        System.out.println("AM или PM: " + (localTime.get(ChronoField.AMPM_OF_DAY)==0?"AM":"PM"));
        System.out.println("Часы: " + localTime.get(ChronoField.HOUR_OF_AMPM));
        System.out.println("Часы дня: " + localTime.getHour());
        System.out.println("Минуты: " + localTime.getMinute());
        System.out.println("Секунды: " + localTime.getSecond());
    }
}
