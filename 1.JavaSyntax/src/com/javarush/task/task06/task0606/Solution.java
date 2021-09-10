package com.javarush.task.task06.task0606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;                                                  
                                                  
/*                                                   
Чётные и нечётные циферки                                                  
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String b = reader.readLine();
        int count = b.length();

        for (int i = 0; i < count; i++) {
            char ch = b.charAt(i);
            int c = Character.getNumericValue(ch);
            if (c != 0) {
                if (c % 2 == 0)
                    even++;
                else odd++;
            }
        }
        System.out.println("Even: " + even + " Odd: " + odd);
    }
}