package com.javarush.task.task30.task3001;

import java.math.BigInteger;

/*
Конвертер систем счислений
*/
public class Solution {

   public static void main(String[] args) {
      Number number = new Number(NumerationSystemType._10, "112");
      Number result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._3);
      System.out.println(result);    //expected 110

      number = new Number(NumerationSystemType._16, "6df");
      result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._8);
      System.out.println(result);    //expected 3337
   }

   public static Number convertNumberToOtherNumerationSystem(Number number, NumerationSystem expectedNumerationSystem) {
      if (number.getDigit().startsWith("-"))
         throw new NumberFormatException();
      int currentNumSys = number.getNumerationSystem().getNumerationSystemIntValue();
         BigInteger integer = new BigInteger(number.getDigit(),currentNumSys);
         return new Number(expectedNumerationSystem, integer.toString(expectedNumerationSystem.getNumerationSystemIntValue()));
   }

   private static Number convert (Number number, NumerationSystem expectedNumerationSystem){
      String newIntStr = number.getDigit();
      if (newIntStr.startsWith("-") || newIntStr.contains(".") || newIntStr.contains(","))
         throw new NumberFormatException();
      int currentNumSys = number.getNumerationSystem().getNumerationSystemIntValue();

      char[] chars = newIntStr.toUpperCase().toCharArray();
      int[] digits = new int[chars.length];

      int result = 0;
      if (newIntStr.matches("\\d+")) {
         for (int i = 0; i < chars.length; i++) {
            if (chars[i] - 48 >= currentNumSys) {throw new NumberFormatException();}
            digits[chars.length - 1 - i] = chars[i] - 48;
            result += digits[chars.length - 1 - i] * (int) Math.pow(currentNumSys, chars.length - 1 - i);
         }
      }
      else {
         for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 57) {
               if (chars[i] - 55 >= currentNumSys) {throw new NumberFormatException();}
               digits[chars.length - 1 - i] = chars[i] - 55;
            }
            else
               digits[chars.length - 1 - i] = chars[i] - 48;
            result += digits[chars.length - 1 - i] * (int) Math.pow(currentNumSys, chars.length - 1 - i);
         }
      }

      int resultNumSys = expectedNumerationSystem.getNumerationSystemIntValue();

      if (resultNumSys != 10)
         newIntStr = countToResult("" + result, resultNumSys);
      return new Number(expectedNumerationSystem, newIntStr);
   }

   private static String countToResult(String newInt, int resultNumSys) {
      StringBuilder builder = new StringBuilder();
      int Int = Integer.parseInt(newInt);
      while (Int / resultNumSys != 0) {
         builder.insert(0, Int % resultNumSys);
         Int = Int / resultNumSys;
      }
      builder.insert(0, Int % resultNumSys);
      return builder.toString();
   }
}
