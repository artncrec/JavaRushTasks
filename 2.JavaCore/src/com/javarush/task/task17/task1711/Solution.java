package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        if (args[0].equals("-c"))       //-c name1 sex1 bd1 name2 sex2 bd2 ... добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
        {
            for (int i = 1; i < args.length; i += 3) {
                addPeople(args[i], args[i + 1], args[i + 2], format);
            }
        }
        else if (args[0].equals("-u"))  //-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ... обновляет соответствующие данные людей с заданными id
        {
            for (int i = 1; i < args.length; i += 4) {
                updatePeople(Integer.parseInt(args[i]), args[i + 1], args[i + 2], args[i + 3], format);
            }
        }
        else if (args[0].equals("-d"))  //-d id1 id2 id3 id4 ... производит логическое удаление всех людей с заданными id
        {
            for (int i = 1; i < args.length; i++) {
                int index = Integer.parseInt(args[i]);
                allPeople.get(index).setBirthDay(null);
                allPeople.get(index).setName(null);
                allPeople.get(index).setSex(null);
            }
        }
        else if (args[0].equals("-i"))  //-i id1 id2 id3 id4 ... выводит на экран информацию о всех людях с заданными id: name sex bd
        {
            for (int i = 1; i < args.length; i++) {
                int index = Integer.parseInt(args[i]);
                String s;
                format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                if (allPeople.get(index).getSex() == Sex.MALE)
                    s = "м";
                else s = "ж";
                System.out.println(allPeople.get(index).getName() + " " + s + " " + format.format(allPeople.get(index).getBirthDay()));
            }
        }
    }

    private static void updatePeople(int index, String name, String sex, String bd, SimpleDateFormat format) throws ParseException {
        allPeople.get(index).setName(name);
        if (sex.equals("м")) {
            allPeople.get(index).setSex(Sex.MALE);
        }
        else {
            allPeople.get(index).setSex(Sex.FEMALE);
        }
        allPeople.get(index).setBirthDay(format.parse(bd));
    }

    private static void addPeople(String name, String sex, String bd, SimpleDateFormat format) throws ParseException {
        if (sex.equals("м")) {
            allPeople.add(Person.createMale(name, format.parse(bd)));
        }
        else if (sex.equals("ж")) {
            allPeople.add(Person.createFemale(name, format.parse(bd)));
        }
        System.out.println(allPeople.size() - 1);
    }
}
