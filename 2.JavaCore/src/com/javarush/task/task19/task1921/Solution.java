package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        ArrayList<String> data = new ArrayList<>();
        String s;
        while ((s = reader.readLine()) != null)
        {
            data.add(s.replace("\uFEFF", ""));
        }
        reader.close();
        int day, month, year;
        String[] line;
        for (String l:data)
        {
            line = l.split(" ");
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < line.length-3; i++)
            {
                builder.append(line[i]);
                if (i < line.length-4)
                {
                    builder.append(' ');
                }
            }
            SimpleDateFormat format = new SimpleDateFormat("d-M-y");
            PEOPLE.add(new Person(builder.toString(), format.parse(line[line.length-3]+"-"+line[line.length-2]+"-"+line[line.length-1])));
        }
    }
}
