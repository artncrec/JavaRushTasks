package com.javarush.task.task05.task0512;

/* 
Создать класс Circle
*/

public class Circle {
    private int centerX;
    private int centerY;
    private int radius;
    private int width;
    private String color;

    public void initialize(int a, int b, int c) {
        this.centerX = a;
        this.centerY = b;
        this.radius = c;
    }

    public void initialize(int a, int b, int c, int d) {
        this.centerX = a;
        this.centerY = b;
        this.radius = c;
        this.width = d;
    }

    public void initialize(int a, int b, int c, int d, String s) {
        this.centerX = a;
        this.centerY = b;
        this.radius = c;
        this.width = d;
        this.color = s;
    }

    public static void main(String[] args) {

    }
}
