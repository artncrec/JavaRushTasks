package com.javarush.task.task25.task2506;

/**
 * Created by admin on 28.06.2017.
 */
class LoggingStateThread extends Thread {
    private Thread thread;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        State st = thread.getState();
        System.out.println(st);
        while (thread.getState() != State.TERMINATED) {
            if (thread.getState() != st)
            {
                System.out.println(thread.getState());
                st = thread.getState();
            }
        }
        this.interrupt();
    }
}
