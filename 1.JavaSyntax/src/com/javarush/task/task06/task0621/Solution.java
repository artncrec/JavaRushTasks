package com.javarush.task.task06.task0621;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Родственные связи кошек
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String Name = reader.readLine();
        Cat catGrfather = new Cat(Name);

        Name = reader.readLine();
        Cat catGrmother = new Cat(Name);

        Name = reader.readLine();
        Cat catFather = new Cat(Name, catGrfather, null);

        Name = reader.readLine();
        Cat catMother = new Cat(Name, null, catGrmother);

        Name = reader.readLine();
        Cat catSon = new Cat(Name, catFather, catMother);

        Name = reader.readLine();
        Cat catDaughter = new Cat(Name, catFather, catMother);

        System.out.println(catGrfather);
        System.out.println(catGrmother);
        System.out.println(catFather);
        System.out.println(catMother);
        System.out.println(catSon);
        System.out.println(catDaughter);
    }

    public static class Cat {
        private String name;
        private Cat mother;
        private Cat father;

        Cat(String name) {
            this.name = name;
        }

        public Cat(String name, Cat father, Cat mother) {
            this.name = name;
            this.father = father;
            this.mother = mother;
        }

        @Override
        public String toString() {
            if (mother == null && father == null)
                return "Cat name is " + name + ", no mother, no father";
            else {
                if (father == null)
                    return "Cat name is " + name + ", mother is " + mother.name + ", no father";
                if (mother == null)
                    return "Cat name is " + name + ", no mother, father is " + father.name;
                return "Cat name is " + name + ", mother is " + mother.name + ", father is " + father.name;
            }
        }
    }
}
