package com.javarush.task.task07.task0722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Это конец
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> list = new ArrayList<>();
        int z = 0;
        String t;
        while (true) {
            t = reader.readLine();
            if (t.equals("end"))
                break;
            list.add(z, t);
            z++;
        }
        for (int x = 0; x < list.size(); x++)
            System.out.println(list.get(x));
    }
}