package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by admin on 05.07.2017.
 */
public class Producer implements Runnable {
    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> concurrentHashMap) {
        this.map = concurrentHashMap;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (true) {
                map.put(++i + "", String.format("Some text for %s", i));
                Thread.sleep(500);
            }
        } catch (Exception e) {
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }
    }
}
