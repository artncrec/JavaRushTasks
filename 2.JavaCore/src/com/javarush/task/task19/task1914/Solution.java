package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream original = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        testString.printSomething();
        System.setOut(original);
        String[] array = outputStream.toString().split(" ");
        if (array[1].equals("+")) {
            System.out.println(array[0] + " + " + array[2] + " = " + (Integer.parseInt(array[0]) + Integer.parseInt(array[2])));
        }
        else if (array[1].equals("-")) {
            System.out.println(array[0] + " - " + array[2] + " = " + (Integer.parseInt(array[0]) - Integer.parseInt(array[2])));
        }
        else if (array[1].equals("*")) {
            System.out.println(array[0] + " * " + array[2] + " = " + (Integer.parseInt(array[0]) * Integer.parseInt(array[2])));
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}
