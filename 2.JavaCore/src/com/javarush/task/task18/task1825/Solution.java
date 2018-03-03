package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName;
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!(fileName = reader.readLine()).equals("end")) {
            list.add(fileName);
        }
        reader.close();
        for (int i = 0; i < list.size(); i++)
            for (int j = i; j < list.size(); j++) {
                String[] part1 = list.get(i).split("\\.");
                String[] part2 = list.get(j).split("\\.");
                int num1 = Integer.parseInt(part1[part1.length - 1].substring(4));
                int num2 = Integer.parseInt(part2[part2.length - 1].substring(4));
                if (num2 < num1) {
                    String s = list.get(j);
                    list.set(j, list.get(i));
                    list.set(i, s);
                }
            }
        String[] part = list.get(0).split("\\.");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < part.length - 1; i++) {
            sb.append(part[i]);
            if (i != part.length - 2)
                sb.append(".");
        }
        String newFile = sb.toString();
        File result = new File(newFile);
        result.createNewFile();
        BufferedOutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(newFile));
        BufferedInputStream inputStream1 = null;
        for (int i = 0; i < list.size(); i++) {
            inputStream1 = new BufferedInputStream(new FileInputStream(list.get(i)));
            byte[] inf = new byte[inputStream1.available()];
            inputStream1.read(inf);
            outputStream1.write(inf);
            outputStream1.flush();
        }
        inputStream1.close();
        outputStream1.close();
    }
}
