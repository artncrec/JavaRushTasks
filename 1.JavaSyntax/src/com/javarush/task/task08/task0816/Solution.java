package com.javarush.task.task08.task0816;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Добрая Зинаида и летние каникулы
*/

public class Solution {
    public static HashMap<String, Date> createMap() {
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Stallone", new Date("JUNE 1 1980"));
        map.put("Stallone1", new Date("JUNE 2 1980"));
        map.put("Stallone2", new Date("JUNE 3 1980"));
        map.put("Stallone3", new Date("JUNE 4 1980"));
        map.put("Stallone4", new Date("JUNE 5 1980"));
        map.put("Stallone5", new Date("JUNE 6 1980"));
        map.put("Stallone6", new Date("JUNE 7 1980"));
        map.put("Stallone7", new Date("JUNE 8 1980"));
        map.put("Stallone8", new Date("JUNE 9 1980"));
        map.put("Stallone9", new Date("APRIL 1 1980"));

        return map;
    }

    public static void removeAllSummerPeople(HashMap<String, Date> map) {
        Iterator<Map.Entry<String, Date>> iterator = map.entrySet().iterator();
        Map.Entry<String, Date> pair;
        while (iterator.hasNext()) {
            pair = iterator.next();
            if (pair.getValue().getMonth() > 4 && pair.getValue().getMonth() < 8)
                iterator.remove();
        }
        for (Map.Entry<String, Date> s : map.entrySet())
            System.out.println(s.getKey() + " - " + s.getValue());
    }

    public static void main(String[] args) {

    }
}
