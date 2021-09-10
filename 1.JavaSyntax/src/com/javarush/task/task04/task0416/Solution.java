package com.javarush.task.task04.task0416;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Переходим дорогу вслепую
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double x = Double.parseDouble(reader.readLine()) % 5;
        if (x < 3) System.out.println("зелёный");
        if (3 <= x && x < 4) System.out.println("жёлтый");
        if (4 <= x && x < 5) System.out.println("красный");
    }
}