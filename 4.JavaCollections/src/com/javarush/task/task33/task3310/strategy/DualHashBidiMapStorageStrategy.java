package com.javarush.task.task33.task3310.strategy;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;

public class DualHashBidiMapStorageStrategy implements StorageStrategy {

    // data с типом DualHashBidiMap.
    DualHashBidiMap data = new DualHashBidiMap();

    @Override
    public boolean containsKey(Long key) {
        if (data.containsKey(key)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        if (data.containsValue(value)) {
            return true;
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        data.put(key, value);

    }

    @Override
    public Long getKey(String value) {

        return (Long) data.getKey(value);
    }

    @Override
    public String getValue(Long key) {
        return (String) data.get(key);
    }
}