package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {

    public void testStorage(Shortener shortener){
        final String text1 = "It is a test string!";
        final String text2 = "Other string for test!";
        final String text3 = "It is a test string!";
        final Long id_1 = shortener.getId(text1);
        final Long id_2 = shortener.getId(text2);
        final Long id_3 = shortener.getId(text3);

        Assert.assertNotEquals(id_2, id_1);
        Assert.assertNotEquals(id_2, id_3);
        Assert.assertEquals(id_1, id_3);
        final String retunedText_1 = shortener.getString(id_1);
        final String retunedText_2 = shortener.getString(id_2);
        final String retunedText_3 = shortener.getString(id_3);
        Assert.assertEquals(text1, retunedText_1);
        Assert.assertEquals(text2, retunedText_2);
        Assert.assertEquals(text3, retunedText_3);

    }

    @Test
    public void testHashMapStorageStrategy(){
        HashMapStorageStrategy strategy1 = new HashMapStorageStrategy();
        Shortener shortener1 = new Shortener(strategy1);
        testStorage(shortener1);
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