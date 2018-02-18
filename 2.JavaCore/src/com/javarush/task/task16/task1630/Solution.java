package com.javarush.task.task16.task1630;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    static {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
            reader.close();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface {
        private String name;
        private StringBuilder data = new StringBuilder();

        @Override
        public void setFileName(String fullFileName) {
            this.name = fullFileName;
        }

        @Override
        public String getFileContent() {
            return data.toString();
        }

        @Override
        public void start() {
            this.run();
        }

        @Override
        public void run() {
            String t;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(name));
                while ((t = reader.readLine()) != null) {
                    data.append(t);
                    data.append(" ");
                }
                reader.close();
            } catch (Exception e) {
            }
        }
    }
}
