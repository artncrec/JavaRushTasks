package com.javarush.task.task14.task1419;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //it's first exception
        try {
            float i = 1 / 0;

        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            int[] i = new int[1];
            System.out.println(i[1]);
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            InputStream stream = new FileInputStream("C:\\1.txt");
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            OutputStream stream = new FileOutputStream("C:\\1.txt");
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            String s = "s";
            int i = Integer.parseInt(s) / 2;
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            Object i = null;
            i = (int) i / 2;
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            int i = -1;
            int[] j = new int[i];
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            int[] i = new int[1];
            System.out.println(i[1]);
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            InputStream stream = new FileInputStream("C:\\1.txt");
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            OutputStream stream = new FileOutputStream("C:\\1.txt");
        } catch (Exception e) {
            exceptions.add(e);
        }
    }
}
