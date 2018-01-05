package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(200);
    }

    public void createExpression(int number) {
        char[] chars = Integer.toString(number, 3).toCharArray();
        int[] ints = new int[chars.length];
        for (int i = 0, n = chars.length; i < n; i++) {
            ints[n - 1 - i] = chars[i]-48;
        }
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] == 0)
                if (list.size() <= i) {
                    list.add('0');
                }
            if (ints[i] == 1)
                if (list.size() <= i) {
                    list.add('+');
                }
                else {
                    list.set(i, '-');
                    list.add('+');
                }
            if (ints[i] == 2)
            {
                if (list.size() > i) {
                    if (list.get(i) == '+') {
                        list.set(i, '0');
                        list.add('+');
                    }
                }
                else
                {
                    list.add('-');
                    list.add('+');
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != '0') {
                builder.append(' ');
                builder.append(list.get(i));
                builder.append(' ');
                builder.append((int)Math.pow(3, i));
            }
        }
        System.out.println(number + " =" + builder.toString());
    }
}