package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String resultFileName = args[0];
        List<Path> listZipFiles = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            listZipFiles.add(Paths.get(args[i]));
        }
        Collections.sort(listZipFiles);
        ZipInputStream zipInputStream;
        FileOutputStream outputStream = new FileOutputStream(resultFileName);
        Vector<InputStream> streams = new Vector<>();
        for (int i = 0; i < listZipFiles.size(); i++) {
            streams.add(new FileInputStream(listZipFiles.get(i).toString()));
        }
        SequenceInputStream sequenceInputStream = new SequenceInputStream(streams.elements());

        zipInputStream = new ZipInputStream(sequenceInputStream);
        while (zipInputStream.getNextEntry() != null) {
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = zipInputStream.read(bytes)) > 0)
                outputStream.write(bytes, 0, len);
            zipInputStream.closeEntry();
        }
        sequenceInputStream.close();
        zipInputStream.close();
        outputStream.close();
    }
}
