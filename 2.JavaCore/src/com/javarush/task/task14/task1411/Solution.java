package com.javarush.task.task14.task1411;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
User, Loser, Coder and Proger
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Person person = null;
        String key = null;

        String[] strings = new String[]{"user", "loser", "coder", "proger"};
        ArrayList<String> list = new ArrayList<>();
        boolean loop;
        while (true) {
            loop = false;
            key = reader.readLine();
            for (String s : strings) {
                if (key.equals(s)) {
                    list.add(key);
                    loop = true;
                }
            }
            if (!loop)
                break;
        }
        reader.close();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("user"))
                person = new Person.User();
            if (list.get(i).equals("loser"))
                person = new Person.Loser();
            if (list.get(i).equals("coder"))
                person = new Person.Coder();
            if (list.get(i).equals("proger"))
                person = new Person.Proger();
            doWork(person); //вызываем doWork
        }
    }

    public static void doWork(Person person) {
        if (person instanceof Person.User)
            ((Person.User) person).live();
        if (person instanceof Person.Loser)
            ((Person.Loser) person).doNothing();
        if (person instanceof Person.Coder)
            ((Person.Coder) person).coding();
        if (person instanceof Person.Proger)
            ((Person.Proger) person).enjoy();
    }
}
