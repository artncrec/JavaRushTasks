package com.javarush.task.task05.task0517;

/* 
Конструируем котиков
*/

public class Cat {
    private String name = null;
    private int weight = 5, age = 2;
    private String color = null, adres = null;

    public Cat(String name) {
        this.name = name;
    }

    public Cat(String name, int a, int b) {
        this.name = name;
        this.weight = a;
        this.age = b;
    }

    public Cat(String name, int a) {
        this.name = name;
        this.age = a;
    }

    public Cat(int a, String color) {
        this.weight = a;
        this.color = color;
    }

    public Cat(int a, String s, String z) {
        this.weight = a;
        this.color = s;
        this.adres = z;
    }

    public static void main(String[] args) {

    }
}
