package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        PrepareMyTest test;
        if (c.isAnnotationPresent(PrepareMyTest.class)) {
            test = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            String[] result = test.fullyQualifiedNames();
            for (String s : result)
                System.out.println(s);
            return true;
        }
        else return false;
    }

    public static boolean printValues(Class c) {
        PrepareMyTest test;
        if (c.isAnnotationPresent(PrepareMyTest.class)) {
            test = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            Class[] result = test.value();
            for (Class s : result)
                System.out.println(s);
            return true;
        }
        else return false;
    }
}
