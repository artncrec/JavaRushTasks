package com.javarush.task.task08.task0817;

import java.util.*;

/* 
Нам повторы не нужны
*/

public class Solution {
    public static HashMap<String, String> createMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Пугачева", "Алла");
        map.put("Басков", "Николай");
        map.put("Джигурда", "Никита");
        map.put("Чехов", "Сосо");
        map.put("Павлиашвили", "Сосо");
        map.put("Абв", "Влад");
        map.put("Вгд", "Влад");
        map.put("Лепс", "Влад");
        map.put("Д'Арк", "Сосо");
        map.put("Климов", "Джордж");
        return map;
    }

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map) {
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        String name;
        ArrayList<String> list = new ArrayList<>();
        while (iterator.hasNext()) {
            name = iterator.next().getValue();
            int count = 0;
            for (String s : map.values()) {
                if (name.equals(s)) {
                    count++;
                    if (count > 1) {
                        list.add(name);
                        break;
                    }
                }
            }
        }
        for (String s : list)
            removeItemFromMapByValue(map, s);

        for (Map.Entry<String, String> pair : map.entrySet())
            System.out.println(pair.getKey() + " " + pair.getValue());
    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value) {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }

    public static void main(String[] args) {
        removeTheFirstNameDuplicates(createMap());
    }
}
