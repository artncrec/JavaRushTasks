package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner {
        private final Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            Person person = null;
            String name1, name2, name3;
            StringBuilder builder = new StringBuilder();
            if (fileScanner.hasNextLine()) {
                name3 = fileScanner.next();
                name1 = fileScanner.next();
                name2 = fileScanner.next();
                builder.append(fileScanner.next());
                builder.append(fileScanner.next());
                builder.append(fileScanner.next());
                DateFormat format = new SimpleDateFormat("ddMMyyyy");
                Date date = null;
                try {
                    date = format.parse(builder.toString());
                } catch (ParseException e) {
                }
                person = new Person(name1, name2, name3, date);
            }
            return person;
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
