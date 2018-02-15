package com.javarush.task.task13.task1318;

import java.io.*;
import java.util.Scanner;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        InputStream read = new FileInputStream(r.readLine());
        r.close();
        while (read.available() > 0) {
            System.out.print((char) read.read());
        }
        read.close();
    }
}