package com.javarush.task.task02.task0213;

/* 
Питомцам нужны люди
*/
public class Solution {
    public static void main(String[] args) {
        Woman Oksana = new Woman();
        Cat Barsik = new Cat();
        Dog Barbos = new Dog();
        Fish Nemo = new Fish();
        Barsik.owner = Oksana;
        Barbos.owner = Oksana;
        Nemo.owner = Oksana;
    }

    public static class Cat {
        public Woman owner;
    }

    public static class Dog {
        public Woman owner;
    }

    public static class Fish {
        public Woman owner;
    }

    public static class Woman {
    }
}
