package com.javarush.task.task04.task0442;


/* 
Суммирование
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int sum = 0;
        while (true) {
            line = reader.readLine();
            sum += Integer.parseInt(line);
            if (line.equals("-1")) break;
        }
        System.out.println(sum);
    }
}
