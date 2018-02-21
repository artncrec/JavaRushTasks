package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream stream = new FileInputStream(reader.readLine());
        reader.close();
        Map<Integer, Integer> list = new HashMap<>();
        if (stream.available() > 0) {
            int data, c;
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
        int max = 0;
        for (Map.Entry entry : list.entrySet()) {
            if ((int) entry.getValue() > max)
                max = (int) entry.getValue();
        }
        for (Map.Entry entry : list.entrySet()) {
            if ((int) entry.getValue() == max)
                System.out.print(entry.getKey() + " ");
        }
    }
}
