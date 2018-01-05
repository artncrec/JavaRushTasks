package com.javarush.task.task25.task2512;

import java.util.LinkedList;
import java.util.List;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        List<Throwable> list = new LinkedList<>();
        Throwable throwable = e;
        list.add(e);
        while ((throwable = throwable.getCause()) != null)
            list.add(throwable);
        for (int i = list.size()-1; i >= 0; i--) {
            System.out.println(list.get(i));
        }
    }
}
