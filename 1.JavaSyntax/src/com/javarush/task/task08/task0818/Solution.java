package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("1", 100);
        map.put("2", 200);
        map.put("3", 300);
        map.put("4", 400);
        map.put("5", 500);
        map.put("6", 600);
        map.put("7", 700);
        map.put("8", 800);
        map.put("9", 900);
        map.put("10", 1000);
        return map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String, Integer> pair = iterator.next();
            if (pair.getValue()<500)
                iterator.remove();
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = createMap();
        removeItemFromMap(map);
    }
}