package com.javarush.task.task39.task3908;

import java.util.HashMap;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("aabcba"));
    }

    public static boolean isPalindromePermutation(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = s.toLowerCase().toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(chars[i])) {
                int j = map.get(chars[i]);
                map.put(chars[i], ++j);
            } else
                map.put(chars[i], 1);
        }
        int count = 0;
        for (int i : map.values())
            if (i % 2 == 1)
                count++;
        return count < 2;
    }
}
