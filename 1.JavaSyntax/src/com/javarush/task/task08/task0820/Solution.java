package com.javarush.task.task08.task0820;

import java.util.HashSet;
import java.util.Set;

/* 
Множество всех животных
*/

public class Solution {
    public static void main(String[] args) {
        Set<Cat> cats = createCats();
        Set<Dog> dogs = createDogs();

        Set<Object> pets = join(cats, dogs);
        printPets(pets);

        removeCats(pets, cats);
        printPets(pets);
    }

    public static Set<Cat> createCats() {
        HashSet<Cat> result = new HashSet<Cat>();

        result.add(new Cat());
        result.add(new Cat());
        result.add(new Cat());
        result.add(new Cat());

        return result;
    }

    public static Set<Dog> createDogs() {
        HashSet<Dog> result = new HashSet<>();

        result.add(new Dog());
        result.add(new Dog());
        result.add(new Dog());

        return result;
    }

    public static Set<Object> join(Set<Cat> cats, Set<Dog> dogs) {
        Set<Object> result = new HashSet<>();
        for (Object s : cats)
            result.add(s);
        for (Object s : dogs)
            result.add(s);
        return result;
    }

    public static void removeCats(Set<Object> pets, Set<Cat> cats) {
        for (Object s : cats)
            pets.remove(s);
    }

    public static void printPets(Set<Object> pets) {
        for (Object s : pets)
            System.out.println(s);
    }

    public static class Cat {
    }

    public static class Dog {
    }
}