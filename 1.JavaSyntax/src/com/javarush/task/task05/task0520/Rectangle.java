package com.javarush.task.task05.task0520;

/* 
Создать класс прямоугольник (Rectangle)
*/


public class Rectangle {
    private int left = 0, top = 0, width = 0, height = 0;

    public Rectangle(int a, int b, int c, int d) {
        this.left = a;
        this.top = b;
        this.width = c;
        this.height = d;
    }

    public Rectangle(int a, int b) {
        this.left = a;
        this.top = b;
    }

    public Rectangle(int a, int b, int c) {
        this.left = a;
        this.top = b;
        this.width = c;
        this.height = c;
    }

    public Rectangle(Rectangle copy) {
        this.left = copy.left;
        this.top = copy.top;
        this.width = copy.width;
        this.height = copy.height;
    }

    public static void main(String[] args) {

    }
}
