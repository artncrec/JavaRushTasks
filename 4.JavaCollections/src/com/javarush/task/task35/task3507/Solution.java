package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static class MyClassLoader extends ClassLoader {
        public Class<?> load(String name) {
            String className = Solution.class.getPackage().getName() + ".data." + Paths.get(name).getFileName().toString().replace(".class", "");
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(name));
                return defineClass(className, bytes, 0 , bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> animals = new HashSet<>();

        File folder = new File(pathToAnimals);
        for (File f : folder.listFiles()) {
            Class myClass = new MyClassLoader().load(f.getAbsolutePath());
            try {
                for (Constructor cons : myClass.getConstructors())
                    if (cons.getParameterCount() == 0 && Animal.class.isAssignableFrom(myClass)) {
                        animals.add((Animal) cons.newInstance());
                        break;
                    }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return animals;
    }
}
