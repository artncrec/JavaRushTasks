package com.javarush.task.task30.task3009;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {

   public static void main(String[] args) {
      System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
      System.out.println(getRadix("123"));        //expected output: [6]
      System.out.println(getRadix("5321"));       //expected output: []
      System.out.println(getRadix("27"));         //expected output: []
   }

   private static Set<Integer> getRadix(String s) {
      Set<Integer> integers = new HashSet<>();
      if (!s.matches("\\d+")) {return integers;}

      BigInteger integer = new BigInteger(s, 10);
      String number;
      for (int i = 2; i <= 36; i++) {
         number = integer.toString(i);
         if (isPolindrom(number))
            integers.add(i);
      }
      return integers;
   }

   private static boolean isPolindrom(String number) {
      for (int i = 0; i < number.length() / 2; i++) {
         char[] chars = number.toCharArray();
         if (chars[i] != chars[chars.length - 1 - i])
            return false;
      }
      return true;
   }
}