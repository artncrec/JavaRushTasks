package com.javarush.task.task05.task0516;

/* 
Друзей не купишь
*/

public class Friend {
    private String name;
    private int age = 0;
    private char sex;

    public Friend(String name) {
        this.name = name;
    }

    public Friend(String name, int a) {
        this.name = name;
        this.age = a;
    }

    public Friend(String name, int z, char s) {
        this.name = name;
        this.age = z;
        this.sex = s;
    }

    public static void main(String[] args) {

    }
}
