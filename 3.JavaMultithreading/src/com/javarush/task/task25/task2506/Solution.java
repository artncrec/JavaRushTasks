package com.javarush.task.task25.task2506;

/* 
Мониторинг состояния нити
*/
public class Solution {
    public static void main(String[] args) throws InterruptedException {
        Thread target = new Thread();
        LoggingStateThread loggingStateThread = new LoggingStateThread(target);

        loggingStateThread.start();
        Thread.sleep(50);
        target.start();  //NEW
        Thread.sleep(200); //RUNNABLE
        target.join(200);
        Thread.sleep(200);
        target.interrupt(); //TERMINATED
        Thread.sleep(500);
    }
}
