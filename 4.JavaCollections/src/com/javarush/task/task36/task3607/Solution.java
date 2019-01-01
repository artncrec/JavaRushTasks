package com.javarush.task.task36.task3607;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.*;

/*
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(Queue.class.getClassLoader());
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        myClassLoader classLoader = new myClassLoader();
        List<Class> listClasses = classLoader.load("");

        for (Class<?> clazz : listClasses) {
            Class superClass = clazz;
            List<Class> superClassArray = new ArrayList<>();
            while ((superClass = superClass.getSuperclass()) != null)
                superClassArray.add(superClass);
            for (Class supers : superClassArray)
                for (Class interf : supers.getInterfaces())
                    if (interf.getSimpleName().equals("Queue")) {
                        try {
                            Constructor constructor = clazz.getDeclaredConstructor();
                            constructor.setAccessible(true);
                            List result = (List) constructor.newInstance();
                            result.get(0);
                        } catch (IndexOutOfBoundsException e) {
                            return clazz;
                        } catch (Exception e) {
                        }
                    }
        }
        return null;
    }

    public static class myClassLoader extends ClassLoader {
        public List<Class> load(String name) {
            List<Class> listClasses = new ArrayList<>();
            File file = new File(name);
            for (File f : file.listFiles()) {
                if (f.getName().endsWith(".class")) {
                    try {
                        byte[] bytes = Files.readAllBytes(Paths.get(f.getAbsolutePath()));
                        listClasses.add(defineClass(null, bytes, 0, bytes.length));
                    } catch (IOException e) {
                    }
                }
            }
            return listClasses;
        }
    }
}