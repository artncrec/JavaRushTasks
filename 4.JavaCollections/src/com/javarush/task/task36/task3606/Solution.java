package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replace('.','/') + "/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        File file = new File(packageName);
        classLoader loader = new classLoader();
        for (File f : file.listFiles()) {
            Class cl;
            if (f.getName().endsWith(".class")) {
                cl = loader.load(f.getAbsolutePath());
                if (cl != null && HiddenClass.class.isAssignableFrom(cl))
                    hiddenClasses.add(cl);
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class clazz : hiddenClasses)
            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase()))
                for (Constructor cons : clazz.getDeclaredConstructors()) {
                    if (cons.getParameterTypes().length == 0) {
                        cons.setAccessible(true);
                        try {
                            return (HiddenClass) cons.newInstance();
                        } catch (Exception e) {
                        }
                    }
                }
        return null;
    }

    public static class classLoader extends ClassLoader {
        public Class<?> load(String name) throws ClassNotFoundException {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(name));
                return defineClass(null, bytes, 0, bytes.length);
            } catch (IOException e) {
            }
            return null;
        }
    }
}

