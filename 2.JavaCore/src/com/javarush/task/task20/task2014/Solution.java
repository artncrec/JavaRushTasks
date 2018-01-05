package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable{
    public static void main(String[] args) throws Exception{
        //System.out.println(new Solution(4));
        File your_file_name = File.createTempFile("Save", ".txt", new File("D:\\"));
        OutputStream outputStream = new FileOutputStream(your_file_name);
        InputStream inputStream = new FileInputStream(your_file_name);

        Solution saveObject = new Solution(18);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(saveObject);
        objectOutputStream.close();
        outputStream.close();

        Solution loadedObject = new Solution(-10);

        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        loadedObject = (Solution) objectInputStream.readObject();
        objectInputStream.close();
        inputStream.close();
        System.out.println(saveObject.string.equals(loadedObject.string));
    }

    private final String pattern = "hh mm ss dd MMMM yyyy, EEEE";
    private Date currentDate;
    private int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
