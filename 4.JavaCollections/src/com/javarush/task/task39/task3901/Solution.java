package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.isEmpty())
            return 0;
        int result = -1;
        char[] chars = s.toCharArray();
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (list.contains(chars[i])) {
                if (result < list.size())
                    result = list.size();
                list.clear();
            }
            else list.add(chars[i]);
        }
        return result;
    }
}
