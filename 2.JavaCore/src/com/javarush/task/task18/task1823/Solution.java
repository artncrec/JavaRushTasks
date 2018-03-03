package com.javarush.task.task18.task1823;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName;
            while (!(fileName = reader.readLine()).equals("exit")) {
                new ReadThread(fileName).start();
            }
            reader.close();
        } catch (IOException e) {
        }
    }

    public static class ReadThread extends Thread {
        String fileName;
        Map<Integer, Integer> map = new HashMap<>();

        public ReadThread(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            try {
                FileInputStream inputStream = new FileInputStream(fileName);
                int b;
                while (inputStream.available() > 0) {
                    b = inputStream.read();
                    if (map.containsKey(b)) {
                        map.put(b, map.get(b) + 1);
                    }
                    else {
                        map.put(b, 1);
                    }
                }
                inputStream.close();
                int result = 0, max = 0;
                for (Map.Entry entry : map.entrySet()) {
                    if ((int) entry.getValue() > max) {
                        max = (int) entry.getValue();
                        result = (int) entry.getKey();
                    }
                }
                resultMap.put(fileName, result);
            } catch (Exception e) {
            }
        }
    }
}
