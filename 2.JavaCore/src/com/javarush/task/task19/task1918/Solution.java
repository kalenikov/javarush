package com.javarush.task.task19.task1918;
 
/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String path = consoleReader.readLine();
        consoleReader.close();
        BufferedReader fileReader = new BufferedReader(new FileReader(path));
        StringBuilder stringBuilder = new StringBuilder();
        while (fileReader.ready()) {
            stringBuilder.append(fileReader.readLine());
        }
        fileReader.close();
        String data = stringBuilder.toString();

        String startTag = "<" + args[0];
        String endTag = "</" + args[0] + ">";
        int startTagIndex;
        int endTagIndex;
        int tempStartTagIndex = 0;
        while (tempStartTagIndex != -1) {
            startTagIndex = data.indexOf(startTag, tempStartTagIndex);
            if (startTagIndex == -1) {
                return;
            }
            endTagIndex = data.indexOf(endTag, startTagIndex);

            tempStartTagIndex = data.indexOf(startTag, startTagIndex + 1);
            if (tempStartTagIndex < endTagIndex && tempStartTagIndex != -1) {
                endTagIndex = data.indexOf(endTag, endTagIndex + 1);
            }

            System.out.println(data.substring(startTagIndex, endTagIndex + endTag.length()));


        }


    }
}