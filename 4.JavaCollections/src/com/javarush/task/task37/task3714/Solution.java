package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

/* 
Древний Рим
*/
public class Solution {
    static int[][] numbers = new int[][]{
            {'M',1000},
            {'D',500},
            {'C',100},
            {'L',50},
            {'X',10},
            {'V',5},
            {'I',1},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        int result = 0;
        if (s.matches("[IVXLCDM]+")) {
            char[] romanNumber = s.toCharArray();
            result = getNumber(romanNumber[romanNumber.length - 1]);
            for (int i = romanNumber.length - 1; i > 0; i--) {
                if (getNumber(romanNumber[i]) <= getNumber(romanNumber[i - 1]))
                    result += getNumber(romanNumber[i - 1]);
                else
                    result -= getNumber(romanNumber[i - 1]);
            }
        }
        return result;
    }

    private static int getNumber(char c) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i][0] == c)
                return numbers[i][1];
        }
        return 0;
    }
}
