package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Date startTime = new Date();
        for (String s : strings) {
            shortener.getId(s);
        }
        Date endTime = new Date();
        return endTime.getTime() - startTime.getTime();
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date startTime = new Date();
        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }
        return new Date().getTime() - startTime.getTime();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long> origIds = new HashSet<>();
        long timeForIdsHashMapSS = getTimeToGetIds(shortener1, origStrings, origIds);
        origIds.clear();
        long timeForIdsHashBiMapSS = getTimeToGetIds(shortener2, origStrings, origIds);
        Assert.assertTrue(timeForIdsHashMapSS > timeForIdsHashBiMapSS);
        origStrings.clear();
        long timeForStringsHashMapSS = getTimeToGetStrings(shortener1, origIds, origStrings);
        origStrings.clear();
        long timeForStringsHashBiMapSS = getTimeToGetStrings(shortener2, origIds, origStrings);
        Assert.assertEquals(timeForStringsHashMapSS, timeForStringsHashBiMapSS, 30);

    }

}