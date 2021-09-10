package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

public class Producer implements Runnable {
    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap cMap) {
        this.map = cMap;
    }

    @Override
    public void run() {
        int i = 1;
        try {
            while (true) {
                map.put(String.valueOf(i), "Some text for " + i++);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.printf("%s thread was terminated", Thread.currentThread().getName());
        }
    }
}