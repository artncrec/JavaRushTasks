package com.javarush.task.task09.task0930;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Задача по алгоритмам
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<String>();
        while (true) {
            String s = reader.readLine();
            if (s.isEmpty()) break;
            list.add(s);
        }

        String[] array = list.toArray(new String[list.size()]);
        sort(array);

        for (String x : array) {
            System.out.println(x);
        }
    }

    public static void sort(String[] array) {
        ArrayList<Integer> n = new ArrayList<>();
        ArrayList<Integer> a = new ArrayList<>();
        for (int z = 0; z < array.length; z++) {
            if (isNumber(array[z]))
                n.add(z);
            else a.add(z);
        }
        String t;
        for (int z = 0; z < n.size(); z++)
            for (int i = z; i < n.size(); i++)
                if (!isGreaterThan(array[n.get(z)], array[n.get(i)])) {
                    t = array[n.get(z)];
                    array[n.get(z)] = array[n.get(i)];
                    array[n.get(i)] = t;
                }
        for (int z = 0; z < a.size(); z++)
            for (int i = z; i < a.size(); i++)
                if (isGreaterThan(array[a.get(z)], array[a.get(i)])) {
                    t = array[a.get(z)];
                    array[a.get(z)] = array[a.get(i)];
                    array[a.get(i)] = t;
                }
    }

    // Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }


    // Переданная строка - это число?
    public static boolean isNumber(String s) {
        if (s.length() == 0) return false;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ((i != 0 && c == '-') // есть '-' внутри строки
                  || (!Character.isDigit(c) && c != '-')) // не цифра и не начинается с '-'
            {
                return false;
            }
        }
        return true;
    }
}
