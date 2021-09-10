package com.javarush.task.task04.task0427;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String ix = reader.readLine();
        int b = ix.length();
        int a = Integer.parseInt(ix);
        if (a>0) {
            if (b == 1 && ((a % 2) == 0)) System.out.println("четное однозначное число");
            if (b == 1 && ((a % 2) != 0)) System.out.println("нечетное однозначное число");
            if (b == 2 && ((a % 2) == 0)) System.out.println("четное двузначное число");
            if (b == 2 && ((a % 2) != 0)) System.out.println("нечетное двузначное число");
            if (b == 3 && ((a % 2) == 0)) System.out.println("четное трехзначное число");
            if (b == 3 && ((a % 2) != 0)) System.out.println("нечетное трехзначное число");
        }



    }
}