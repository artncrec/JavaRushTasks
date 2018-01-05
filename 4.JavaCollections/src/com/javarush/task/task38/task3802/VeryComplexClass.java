package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.io.File;
import java.io.FileReader;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        FileReader fileReader = new FileReader(new File("c:/1.txt"));
    }

    public static void main(String[] args) throws Exception{
        new VeryComplexClass().veryComplexMethod();
    }
}
