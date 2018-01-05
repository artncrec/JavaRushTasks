package com.javarush.task.task27.task2705;

import sun.awt.windows.ThemeReader;

/*
Второй вариант deadlock
*/
public class Solution {
    private final Object lock = new Object();

    public synchronized void firstMethod() {
        try{Thread.sleep(2);}
        catch (InterruptedException e){}
        synchronized (lock) {
            doSomething();
        }
    }

    public void secondMethod() {
        synchronized (lock)
        {
            try{Thread.sleep(2);}
            catch (InterruptedException e){}
            synchronized (this)
            {
                doSomething();
            }
        }
    }

    private void doSomething() {
        System.out.println("start");
    }

    class myRun1 implements Runnable{
        Solution solution;

        public myRun1(Solution solution) {
            this.solution = solution;
        }

        @Override
        public void run() {
            solution.firstMethod();
        }
    }

    class myRun2 implements Runnable{
        Solution solution;

        public myRun2(Solution solution) {
            this.solution = solution;
        }

        @Override
        public void run() {
            solution.secondMethod();
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        new Thread(s.new myRun1(s)).start();
        new Thread(s.new myRun2(s)).start();
    }
}