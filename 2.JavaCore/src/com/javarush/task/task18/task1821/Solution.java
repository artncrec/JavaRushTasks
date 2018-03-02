package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("G:\\1.txt");
        int b, count;
        Map<Integer, Integer> map = new HashMap<>();
        while (inputStream.available() > 0) {
            b = inputStream.read();
            if (map.containsKey(b)) {
                count = map.get(b);
                map.remove(b);
                count++;
                map.put(b, count);
            } else {
                map.put(b, 1);
            }
        }
        Integer[] bytes = new Integer[map.size()];
        Integer[] counts = new Integer[map.size()];
        b = 0;
        for (Map.Entry entry : map.entrySet()) {
            bytes[b] = (int) entry.getKey();
            counts[b] = (int) entry.getValue();
            b++;
        }
        for (int i = 0; i < bytes.length; i++)
            for (int j = i; j < bytes.length; j++)
                if (bytes[j] < bytes[i]) {
                    b = bytes[i];
                    bytes[i] = bytes[j];
                    bytes[j] = b;
                    b = counts[i];
                    counts[i] = counts[j];
                    counts[j] = b;
                }
        for (int i = 0; i < bytes.length; i++)
            System.out.println((char) (int) bytes[i] + " " + counts[i]);
    }
}
