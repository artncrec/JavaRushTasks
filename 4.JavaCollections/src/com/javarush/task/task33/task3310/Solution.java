package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> set = new HashSet<>();
        for (String value : strings)
            set.add(shortener.getId(value));
        return set;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> set = new HashSet<>();
        for (Long key : keys)
            set.add(shortener.getString(key));
        return set;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> setV = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            setV.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        Date dateBegin = new Date();
        Set<Long> setK = getIds(shortener, setV);
        Date dateFinish = new Date();
        long test1 = dateFinish.getTime() - dateBegin.getTime();
        System.out.println(test1);

        dateBegin = new Date();
        Set<String> setTest = getStrings(shortener, setK);
        dateFinish = new Date();
        long test2 = dateFinish.getTime() - dateBegin.getTime();
        System.out.println(test2);

        if (setV.containsAll(setTest))
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");
    }

    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new FileStorageStrategy(), 100);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);
    }
}