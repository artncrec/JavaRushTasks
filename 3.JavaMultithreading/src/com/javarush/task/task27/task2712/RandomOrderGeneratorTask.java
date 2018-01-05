package com.javarush.task.task27.task2712;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.currentThread;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets = new ArrayList<>();
    private int sleepTime;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.sleepTime = interval;
    }

    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            int number = (int) (Math.random() * tablets.size());
            tablets.get(number).createTestOrder();
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }
    }
}
