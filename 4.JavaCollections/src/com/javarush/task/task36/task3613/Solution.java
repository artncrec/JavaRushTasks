package com.javarush.task.task36.task3613;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/*
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        File dir = new File(BlockingQueue.class.getPackage().getName().replace(".","/"));
        for (File file:dir.listFiles())
        System.out.println(file.getName());
        return null;
    }
}