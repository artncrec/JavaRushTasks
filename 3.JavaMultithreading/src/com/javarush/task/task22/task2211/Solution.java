package com.javarush.task.task22.task2211;

import java.io.*;

/* 
Смена кодировки
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(args[0]));
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(args[1]));
        byte[] input = new byte[inputStream.available()];
        inputStream.read(input);
        String result = new String(input, "UTF-8");
        outputStream.write(result.getBytes("Windows-1251"));
        inputStream.close();
        outputStream.close();
    }
}
