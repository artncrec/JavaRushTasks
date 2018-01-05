package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.*;

public class FunctionalTest {
    public void testStorage(Shortener shortener){
        String s1, s3 = s1 = Helper.generateRandomString(), s2 = Helper.generateRandomString();
        Long k1, k2, k3;
        k1 = shortener.getId(s1);
        k2 = shortener.getId(s2);
        k3 = shortener.getId(s3);
        Assert.assertNotEquals(k2, k1);
        Assert.assertNotEquals(k2, k3);
        Assert.assertEquals(k1, k3);
        String test1, test2, test3;
        test1 = shortener.getString(k1);
        test2 = shortener.getString(k2);
        test3 = shortener.getString(k3);
        Assert.assertEquals(s1, test1);
        Assert.assertEquals(s2, test2);
        Assert.assertEquals(s3, test3);
    }
    @Test
    public void testHashMapStorageStrategy(){
        HashMapStorageStrategy strategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testOurHashMapStorageStrategy(){
        OurHashMapStorageStrategy strategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testFileStorageStrategy(){
        FileStorageStrategy strategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testHashBiMapStorageStrategy(){
        HashBiMapStorageStrategy strategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testDualHashBidiMapStorageStrategy(){
        DualHashBidiMapStorageStrategy strategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testOurHashBiMapStorageStrategy(){
        OurHashBiMapStorageStrategy strategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
}