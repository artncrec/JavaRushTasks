package com.javarush.task.task10.task1019;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* 
Функциональности маловато!
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> map = new HashMap<>();
        while (true) {
            String number = reader.readLine();
            if (number.equals("")) break;

            String name = reader.readLine();
            if (name.equals("")) break;

            int id = Integer.parseInt(number);
            map.put(name, id);
        }
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey());
        }
    }
}
