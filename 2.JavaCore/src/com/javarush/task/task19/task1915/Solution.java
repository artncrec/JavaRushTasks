package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileOutputStream stream = new FileOutputStream(reader.readLine());
        reader.close();
        PrintStream original = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream0 = new PrintStream(outputStream);
        System.setOut(stream0);
        testString.printSomething();
        System.setOut(original);
        System.out.println(outputStream.toString());
        outputStream.writeTo(stream);
        stream.close();
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
