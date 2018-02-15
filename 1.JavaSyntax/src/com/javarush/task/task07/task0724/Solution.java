package com.javarush.task.task07.task0724;

/* 
Семейная перепись
*/

public class Solution {
    public static void main(String[] args) {
        Human ded = new Human("ded p", true, 80, null,null);
        Human baba = new Human("baba p", false, 75, null,null);
        Human dedm = new Human("ded m", true, 78, null,null);
        Human babam = new Human("baba m", false, 72, null,null);
        Human papa = new Human("papa", true, 50, ded, baba);
        Human mama = new Human("mama", false, 45, dedm, babam);
        Human kid1 = new Human("papa", true, 30, papa, mama);
        Human kid2 = new Human("mama", false, 25, papa, mama);
        Human kid3 = new Human("mama", false, 15, papa, mama);
        System.out.println(ded);
        System.out.println(baba);
        System.out.println(dedm);
        System.out.println(babam);
        System.out.println(papa);
        System.out.println(mama);
        System.out.println(kid1);
        System.out.println(kid2);
        System.out.println(kid3);
    }

    public static class Human {
        String name;
        boolean sex;
        int age;
        Human father, mother;

        public Human(String name, boolean sex, int age, Human father, Human mother) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }
}






















