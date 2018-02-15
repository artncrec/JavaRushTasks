package com.javarush.task.task10.task1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Количество букв
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // алфавит
        String abc = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        char[] abcArray = abc.toCharArray();

        ArrayList<Character> alphabet = new ArrayList<Character>();
        for (int i = 0; i < abcArray.length; i++) {
            alphabet.add(abcArray[i]);
        }

        // ввод строк
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            String s = reader.readLine();
            list.add(s.toLowerCase());
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int z = 0; z < alphabet.size(); z++) {
            int count = 0;
            for (String s : list) {
                char[] chars = s.toCharArray();
                for (char b : chars) {
                    if (alphabet.get(z) == b)
                        count++;
                }
            }
            result.add(z, count);
        }

        for (int s = 0; s < alphabet.size(); s++)
            System.out.println(alphabet.get(s) + " " + result.get(s));
    }
}
