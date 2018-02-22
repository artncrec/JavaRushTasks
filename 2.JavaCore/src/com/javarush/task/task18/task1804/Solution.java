package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream stream = new FileInputStream(reader.readLine());
        reader.close();
        Map<Integer, Integer> list = new HashMap<>();
        int data = 0;
        if (stream.available() > 0) {
            int c;
            while (stream.available() > 0) {
                data = stream.read();
                if (list.containsKey(data)) {
                    c = list.get(data);
                    list.remove(data);
                    list.put(data, ++c);
                }
                else list.put(data, 1);
            }
        }
        stream.close();
        for (Map.Entry entry : list.entrySet()) {
            if ((int) entry.getValue() < data)
                data = (int) entry.getValue();
        }
        for (Map.Entry entry : list.entrySet()) {
            if ((int) entry.getValue() == data)
                System.out.print(entry.getKey() + " ");
        }
    }
}
