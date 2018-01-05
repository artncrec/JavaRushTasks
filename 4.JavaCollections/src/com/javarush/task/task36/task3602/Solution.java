package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class<?>[] listClasses = Collections.class.getDeclaredClasses();

        for (Class<?> clazz : listClasses)
            if (clazz.getModifiers() == 10) {
                Class superClass = clazz;
                List<Class> superClassArray = new ArrayList<>();
                while ((superClass = superClass.getSuperclass()) != null)
                    superClassArray.add(superClass);
                for (Class supers : superClassArray)
                    for (Class interf : supers.getInterfaces())
                        if (interf.getSimpleName().equals("List")) {
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
}
