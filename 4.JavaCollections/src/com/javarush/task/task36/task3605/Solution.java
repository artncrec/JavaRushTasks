package com.javarush.task.task36.task3605;

import java.io.*;
import java.util.Iterator;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(args[0]);
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        fileInputStream.close();

        TreeSet<Character> treeSet = new TreeSet();
        for (int i = 0; i < bytes.length; i++) {
            char c = (char) bytes[i];
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z')
                treeSet.add(Character.toLowerCase(c));
        }
        Iterator iterator = treeSet.iterator();
        for (int i = 0; i < 5; i++) {
            if (iterator.hasNext())
                System.out.print(iterator.next());
        }
    }
}
