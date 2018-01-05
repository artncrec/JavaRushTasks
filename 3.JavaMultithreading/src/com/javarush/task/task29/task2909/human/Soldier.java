package com.javarush.task.task29.task2909.human;

/**
 * Created by Arthur on 01.07.2017.
 */
public class Soldier extends Human {
    @Override
    public void live() {
        fight();
    }

    public void fight() {
    }

    public Soldier(String name, int age) {
        super(name, age);
    }
}
