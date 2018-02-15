package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) {
        String[] str = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            str = reader.readLine().split("\\?");
            reader.close();
        } catch (IOException e) {
        }
        ArrayList<String> Parametr = new ArrayList<>();
        ArrayList<String> znach = new ArrayList<>();
        if (str != null) {
            String[] URL = str[1].split("&");
            for (String s : URL) {
                String[] param = s.split("=");
                Parametr.add(param[0]);
                if (param[0].equals("obj"))
                    znach.add(param[1]);
            }
            for (String s : Parametr)
                System.out.print(s + " ");
            if (!znach.isEmpty()) {
                System.out.println();
                for (String s : znach) {
                    try {
                        alert(Double.parseDouble(s));
                    } catch (Exception e) {
                        alert(s);
                    }
                }
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
