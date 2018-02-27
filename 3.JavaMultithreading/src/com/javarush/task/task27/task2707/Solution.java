package com.javarush.task.task27.task2707;

/*
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isNormalLockOrder(final Solution solution, final Object o1, final Object o2) throws Exception {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (o1) {
                        Thread.sleep(50);
                        synchronized (o2) {
                            Thread.sleep(50);
                        }
                    }
                } catch (InterruptedException e) {
                }

            }
        };
        Thread thread1 = new Thread(runnable);
        thread1.setDaemon(true);

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                solution.someMethodWithSynchronizedBlocks(o1, o2);
            }
        };
        thread2.setDaemon(true);

        thread1.start();
        Thread.sleep(20);
        thread2.start();
        Thread.sleep(100);

        if (thread2.getState() != Thread.State.BLOCKED)
            return true;
        return false;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isNormalLockOrder(solution, o1, o2));
    }
}
