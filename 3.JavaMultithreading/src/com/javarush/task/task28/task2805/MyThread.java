package com.javarush.task.task28.task2805;

/**
 * Created by admin on 14.07.2017.
 */
public class MyThread extends Thread {
    private static int i = 0;

    private void setPriority() {
        if (this.getThreadGroup() != null)
            if (i % 10 + 1 > this.getThreadGroup().getMaxPriority())
                this.setPriority(this.getThreadGroup().getMaxPriority());
            else this.setPriority(i % 10 + 1);
        i++;
    }

    public MyThread() {
        super();
        setPriority();
    }

    public MyThread(Runnable target) {
        super(target);
        setPriority();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setPriority();
    }

    public MyThread(String name) {
        super(name);
        setPriority();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setPriority();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setPriority();
    }
}