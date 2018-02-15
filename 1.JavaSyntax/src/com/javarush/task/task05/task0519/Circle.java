package com.javarush.task.task05.task0519;

/* 
Ходим по кругу
*/


public class Circle {
    private int centerX, centerY, radius, width;
    private String color;

    public Circle(int a, int b, int c) {
        this.centerX = a;
        this.centerY = b;
        this.radius = c;
    }

    public Circle(int a, int b, int c, int d) {
        this.centerX = a;
        this.centerY = b;
        this.radius = c;
        this.width = d;
    }

    public Circle(int a, int b, int c, int d, String s) {
        this.centerX = a;
        this.centerY = b;
        this.radius = c;
        this.width = d;
        this.color = s;
    }

    public static void main(String[] args) {

    }
}
