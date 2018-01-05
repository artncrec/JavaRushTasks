package com.javarush.task.task25.task2514;

import java.util.ArrayList;
import java.util.List;

/*
Первый закон Финэйгла: если эксперимент удался, что-то здесь не так...
*/
public class Solution {
    public static class YieldRunnable implements Runnable {
        private int index;

        public YieldRunnable(int index) {
            this.index = index;
        }

        public void run() {
            System.out.println("begin-" + index);
            Thread.yield();
            System.out.println("end-" + index);
        }
    }

    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 999; i++) {
            list.add(new Thread(new YieldRunnable(i)));
        }
        for (Thread t:list)
            t.start();
    }
}