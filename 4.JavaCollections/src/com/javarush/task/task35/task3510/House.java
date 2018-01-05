package com.javarush.task.task35.task3510;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* 
Вход воспрещен!
*/
public class House<T> {
    Class<T> t;

    public House(Class<T> t) {
        this.t = t;
    }

    private List<T> residents = new ArrayList<>();

    public void enter(T resident) {
//        System.out.println(t + " ");
//        if (resident.getClass().equals(t.ge))
            residents.add(resident);
//        else leave(resident);
    }

    public void leave(T resident) {
        if (!residents.isEmpty())
        residents.remove(resident);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("В доме находятся:\n");
        for (Object resident : residents) {
            builder.append(resident.toString()).append("\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Dog bruno = new Dog("Bruno");
        Puppy larsik = new Puppy("Larsik");
        Cat barsik = new Cat("Barsik");
        Kitten keksik = new Kitten("Keksik");

        House dogHouse = new House(Dog.class);
        dogHouse.enter(barsik);
        dogHouse.enter(bruno);
        dogHouse.enter(larsik);
        System.out.println(dogHouse.toString());

        House catHouse = new House(Cat.class);
        catHouse.enter(barsik);
        catHouse.enter(keksik);
        catHouse.enter(bruno);
        System.out.println(catHouse.toString());
    }
}