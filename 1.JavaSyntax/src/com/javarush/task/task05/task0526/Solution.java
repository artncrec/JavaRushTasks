package com.javarush.task.task05.task0526;

/* 
Мужчина и женщина
*/

public class Solution {
    public static void main(String[] args) {
        Man man1 = new Man("M", 24, "Moscow");
        Man man2 = new Man("A", 12, "Piter");
        Woman woman1 = new Woman("N", 18, "Omsk");
        Woman woman2 = new Woman("D", 18, "Israel");

        System.out.println(man1.name + " " + man1.age + " " + man1.address);
        System.out.println(man2.name + " " + man2.age + " " + man2.address);
        System.out.println(woman1);
        System.out.println(woman2);
    }

    public static class Man {
        String name, address;
        int age;

        public Man(String n, int a, String add) {
            this.name = n;
            this.age = a;
            this.address = add;
        }
    }

    public static class Woman {
        String name, address;
        int age;

        public Woman(String n, int a, String add) {
            this.name = n;
            this.age = a;
            this.address = add;
        }

        public String toString() {
            return name + " " + age + " " + address;
        }
    }
}
