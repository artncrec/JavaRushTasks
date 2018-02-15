package com.javarush.task.task08.task0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Омовение Рамы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean up = true;
        for (char c : reader.readLine().toCharArray()) {
            if (c != ' ') {
                if (up) {
                    c = Character.toUpperCase(c);
                    up = false;
                }
            } else up = true;
            System.out.print(c);
        }
    }
}
