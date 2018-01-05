package com.javarush.task.task25.task2512;

/**
 * Created by admin on 30.06.2017.
 */
public class Task extends Thread{
    private Thread.UncaughtExceptionHandler handler;
    public Task() {
        handler = new Solution();
    }

    @Override
    public void run() {
        System.out.println("/start");
        handler.uncaughtException(Thread.currentThread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }

    public static void main(String[] args) {
        Task t = new Task();
        Thread thread = new Thread(t);
        thread.run();
    }
}
