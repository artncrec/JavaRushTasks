package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        Path file = Paths.get(fileName);
        if (Files.isDirectory(file)) {
            Visitor visitor = new Visitor();
            Files.walkFileTree(file, visitor);
            System.out.println("Всего папок - " + visitor.getDirs());
            System.out.println("Всего файлов - " + visitor.getFiles());
            System.out.println("Общий размер - " + visitor.getSize());
        }
        else
            System.out.println(fileName + " - не папка");
    }

    private static class Visitor extends SimpleFileVisitor<Path> {
        private int dirs = -1, files = 0;
        private long size = 0;

        public int getDirs() {
            return dirs;
        }

        public int getFiles() {
            return files;
        }

        public long getSize() {
            return size;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            dirs++;
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            files++;
            size += attrs.size();
            return FileVisitResult.CONTINUE;
        }
    }
}
