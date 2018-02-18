package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new infinite());
        threads.add(new interexc());
        threads.add(new Yra());
        threads.add(new msg());
        threads.add(new numb());
    }

    static class infinite extends Thread // 1
    {
        @Override
        public void run() {
            while (true) {
            }
        }
    }

    static class interexc extends Thread // 2
    {
        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    sleep(1);
                }
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }

    static class Yra extends Thread // 3
    {
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("Ура");
                    sleep(500);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    static class msg extends Thread implements Message // 4
    {
        @Override
        public void showWarning() {
            interrupt();
            try {
                join();
            } catch (Exception e) {
            }
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
            }
        }
    }

    static class numb extends Thread // 5
    {
        @Override
        public void run() {
            try {
                int sum = 0;
                String s;
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                while (!(s = reader.readLine()).equals("N")) {
                    sum += Integer.parseInt(s);
                }
                reader.close();
                System.out.println(sum);
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {
    }
}