package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/*
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Map<String, byte[]> zipData = new HashMap<>();
        Path file = Paths.get(args[0]);
        Path zipFile = Paths.get(args[1]);

        ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile));
        ByteArrayOutputStream byteArrayOutputStream;
        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            if (!Paths.get(zipEntry.getName()).getFileName().equals(file.getFileName())) {
                byteArrayOutputStream = new ByteArrayOutputStream();
                copyData(zipInputStream, byteArrayOutputStream);
                zipData.put(zipEntry.getName(), byteArrayOutputStream.toByteArray());
                zipInputStream.closeEntry();
                byteArrayOutputStream.close();
            }
        }
        zipInputStream.close();

        ZipOutputStream outputStream = new ZipOutputStream(Files.newOutputStream(zipFile));
        outputStream.putNextEntry(new ZipEntry(Paths.get("new").resolve(file.getFileName()).toString()));
        Files.copy(file, outputStream);
        outputStream.closeEntry();

        for (Map.Entry<String, byte[]> dataEntry : zipData.entrySet()) {
            outputStream.putNextEntry(new ZipEntry(dataEntry.getKey()));
            outputStream.write(dataEntry.getValue());
            outputStream.closeEntry();
        }
        outputStream.close();
    }

    private static void copyData(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[8 * 1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }
}
