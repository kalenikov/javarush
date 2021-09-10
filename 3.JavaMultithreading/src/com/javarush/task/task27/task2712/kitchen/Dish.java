package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;

public enum Dish {
    FISH(25),
    STEAK(30),
    SOUP(15),
    JUICE(5),
    WATER(3);

    private int duration;

    Dish(int duration) {
        this.duration = duration;
    }

    Dish() {
    }

    public static String allDishesToString() {
        if (values().length == 0) {
            return "";
        }

        return Arrays.toString(values()).substring(1, Arrays.toString(values()).length() - 1);
    }

    public int getDuration() {
        return duration;
    }
}
