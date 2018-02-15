package com.javarush.task.task05.task0513;

/* 
Собираем прямоугольник
*/

public class Rectangle {
    private int left = 0, top = 0, width = 0, height = 0;

    public void initialize(int a, int b, int c, int d) {
        this.left = a;
        this.top = b;
        this.width = c;
        this.height = d;
    }

    public void initialize(int a, int b) {
        this.left = a;
        this.top = b;
    }

    public void initialize(int a, int b, int c) {
        this.left = a;
        this.top = b;
        this.width = c;
        this.height = c;
    }

    public void initialize(Rectangle copy) {
        this.left = copy.left;
        this.top = copy.top;
        this.width = copy.width;
        this.height = copy.height;
    }

    public static void main(String[] args) {

    }
}
