package com.javarush.task.task26.task2610;

import java.util.concurrent.BlockingQueue;

/**
 * Created by admin on 05.07.2017.
 */
public class Consumer implements Runnable {
    private BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(queue.take());
            }
        } catch (InterruptedException e) {
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }
    }
}
