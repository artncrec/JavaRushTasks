package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        File resultFile = new File(args[1]);
        List<File> names;
        File dest = new File(resultFile.toPath().getParent().resolve("allFilesContent.txt").toString());
        FileUtils.renameFile(resultFile, dest);

        try (FileOutputStream outputStream = new FileOutputStream(dest)) {

            names = getFiles(args[0]);

            names.sort((File o1, File o2) -> o1.getName().compareTo(o2.getName()));

            for (File file : names)
                try {
                    FileInputStream inputStream = new FileInputStream(file);
                    while (inputStream.available() > 0)
                        outputStream.write(inputStream.read());
                    outputStream.write("\n".getBytes());
                    outputStream.flush();
                    inputStream.close();
                } catch (IOException e) {
                }
        } catch (IOException e) {
        }
    }

    private static List<File> getFiles(String path){
        List<File> names = new ArrayList<>();
        File folder = new File(path);
        for (File file : folder.listFiles()) {
            if (file.isDirectory())
                names.addAll(getFiles(file.getAbsolutePath()));
            else{
                try {
                    FileInputStream inputStream = new FileInputStream(file);
                    if (file.length() > 50) {
                        FileUtils.deleteFile(file);
                    } else {
                        names.add(file);
                    }
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
        return names;
    }

    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }
}
