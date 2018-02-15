package com.javarush.task.task08.task0824;

/* 
Собираем семейство
*/

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        ArrayList<Human> kids = new ArrayList<>();
        kids.add(new Human("kid1", true, 15));
        kids.add(new Human("kid2", false, 10));
        kids.add(new Human("kid3", false, 10));
        for (Human i : kids) {
            System.out.println(i);
        }

        ArrayList<Human> father = new ArrayList<>();
        father.add(new Human("dad", true, 45, kids));
        System.out.println(father.get(0));
        ArrayList<Human> mother = new ArrayList<>();
        mother.add(new Human("mom", false, 40, kids));
        System.out.println(mother.get(0));

        ArrayList<Human> grandp = new ArrayList<>();
        grandp.add(new Human("ded", true, 70, father));
        grandp.add(new Human("ded2", true, 75, mother));
        grandp.add(new Human("bab", false, 60, father));
        grandp.add(new Human("bab2", false, 65, mother));
        for (Human i : grandp) {
            System.out.println(i);
        }
    }

    public static class Human {
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children = new ArrayList<>();

        public Human(String name, boolean sex, int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public Human(String name, boolean sex, int age, ArrayList<Human> children) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0) {
                text += ", дети: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++) {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }
            return text;
        }
    }

}
