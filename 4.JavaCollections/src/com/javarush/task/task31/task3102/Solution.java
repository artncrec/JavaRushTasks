package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> files = new ArrayList<>();
        File folder;
        Queue<File> queue = new LinkedBlockingQueue<>();
        queue.add(new File(root));
        while (!queue.isEmpty()) {
            folder = queue.poll();
            for (File file : folder.listFiles()) {
                if (file.isDirectory())
                    queue.add(file);
                else
                    files.add(file.getAbsolutePath());
            }
        }
        return files;
    }

    public static void main(String[] args) {
        
    }
}
