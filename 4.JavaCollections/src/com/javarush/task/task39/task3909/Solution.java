package com.javarush.task.task39.task3909;

/*
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isOneEditAway("1234", "12034"));
    }

    public static boolean isOneEditAway(String first, String second) {
        if (first == null || second == null)
            return false;
        if (first.length() - second.length() > 1 || first.length() - second.length() < -1)
            return false;
        else{
            return compareString(first, second);
        }
    }

    private static boolean compareString(String first, String second) {
        char[] var1 = first.toCharArray();
        char[] var2 = second.toCharArray();
        int count = 0;
        if (var1.length == var2.length) {
            for (int i = 0; i < var1.length; i++) {
                if (var1[i] != var2[i])
                    count++;
            }
        } else if (var1.length != 0 && var2.length != 0) {
            for (int i = 0, j = 0; i < var1.length && j < var2.length; i++, j++) {
                if (var1[i] != var2[j]) {
                    if (var1.length > var2.length)
                        j--;
                    else
                        i--;
                    count++;
                }
            }
            if (count == 0)
                return true;
        } else return true;
        return count <= 1;
    }
}
