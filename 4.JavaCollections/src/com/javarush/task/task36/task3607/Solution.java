package com.javarush.task.task36.task3607;

import java.util.Collection;
import java.util.*;

/*
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        String listClass = Solution.class.getProtectionDomain().getCodeSource().getLocation().toString();
//        for (Class c:listClass)
//            System.out.println(c);
        
        System.out.println(listClass);
        return null;
    }
}