package com.javarush.task.task22.task2212;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
Проверка номера телефона

Метод checkTelNumber должен проверять, является ли аргумент telNumber валидным номером телефона.

Критерии валидности:
1) если номер начинается с ‘+‘, то он содержит 12 цифр
2) если номер начинается с цифры или открывающей скобки, то он содержит 10 цифр
3) может содержать 0-2 знаков ‘—‘, которые не могут идти подряд
4) может содержать 1 пару скобок ‘(‘ и ‘)‘ , причем если она есть, то она расположена левее знаков ‘-‘
5) скобки внутри содержат четко 3 цифры
6) номер не содержит букв
7) номер заканчивается на цифру

Примеры:
+380501234567 - true
+38(050)1234567 - true
+38050123-45-67 - true
050123-4567 - true
+38)050(1234567 - false
+38(050)1-23-45-6-7 - false
050ххх4567 - false
050123456 - false
(0)501234567 - false

*/
public class Solution {
  public static boolean checkTelNumber(String telNumber) {
    if (telNumber == null) return false;
    if (telNumber.isEmpty()) return false;
    int digits = telNumber.replaceAll("\\D", "").length();
    if ((telNumber.charAt(0) == '+' && digits == 12) || (telNumber.charAt(0) != '+' && digits == 10)) {
        return telNumber.matches("(\\+\\d+)?\\d*(\\(\\d{3}\\))?\\d+(-?\\d+){0,2}");
    }
    else return false;
}
    public static void main(String[] args) {
        //System.out.println(checkTelNumber("+380501234567"));

        HashMap<String, Boolean> phones = new HashMap<>();
        phones.put("+380501234567", true);
        phones.put("+380501234567", true);
        phones.put("+38(050)1234567", true);
        phones.put("+38050123-45-67", true);
        phones.put("050123-4567", false);
        phones.put("+38)050(1234567", false);
        phones.put("+38(050)1-23-45-6-7", false);
        phones.put("050ххх4567", false);
        phones.put("050123456", false);
        phones.put("(0)501234567", false);

        for (Map.Entry<String, Boolean> pair : phones.entrySet()) {
//            System.out.println(pair.getKey());
            if (checkTelNumber(pair.getKey()) != pair.getValue())
                System.out.println("ERROR:Should be:" + pair.getValue() + " checkTelNumber:" + checkTelNumber(pair.getKey()) + " " + pair.getKey());
        }

    }
}