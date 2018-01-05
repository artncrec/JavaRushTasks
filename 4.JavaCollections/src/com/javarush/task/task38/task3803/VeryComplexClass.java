package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.util.ArrayList;

public class VeryComplexClass {
    public void methodThrowsClassCastException() throws Exception{
        Object i = new Integer(0);
        System.out.println((String)i);
    }

    public void methodThrowsNullPointerException() throws NullPointerException{
        ArrayList<Integer> list = new ArrayList<>();
        list.add(null);
        int i = 1/list.get(0);
    }

    public static void main(String[] args) throws Exception{
    }
}
