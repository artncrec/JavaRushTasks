package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Date dateStart = new Date();
        for (String s:strings)
            ids.add(shortener.getId(s));
        Date dateFinish = new Date();
        return dateFinish.getTime() - dateStart.getTime();
    }
    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Date dateStart = new Date();
        for (Long aLong:ids)
            strings.add(shortener.getString(aLong));
        Date dateFinish = new Date();
        return dateFinish.getTime() - dateStart.getTime();
    }
    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long> ids = new HashSet<>();
        Long time1 = getTimeForGettingIds(shortener1, origStrings, ids);
        Long time2 = getTimeForGettingIds(shortener2, origStrings, ids);
        Assert.assertTrue(time1 > time2);
        time1 = getTimeForGettingStrings(shortener1, ids, origStrings);
        time2 = getTimeForGettingStrings(shortener2, ids, origStrings);
        Assert.assertEquals(time1, time2, 30);
    }
}
