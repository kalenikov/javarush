package com.javarush.task.task39.task3913;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LogParserTest {

    private LogParser logParser;

    private static final String QUERY_INVALID_1 = "";
    private static final String QUERY_INVALID_2 = "get event for date = ";
    private static final String QUERY_INVALID_3 = "get user for =date = \"29.02.2028 05:4:07\"";
    private static final String QUERY_INVALID_4 = "get user for date = \"29.02:2028 05:4:07\"";
    private static final String QUERY_VALID_IP = "get ip";
    private static final String QUERY_VALID_USER = "get user";
    private static final String QUERY_VALID_DATE = "get date";
    private static final String QUERY_VALID_EVENT = "get event";
    private static final String QUERY_VALID_STATUS = "get status";
    private static final String QUERY_VALID_IP_IP = "get ip for ip = \"127.0.0.1\"";
    private static final String QUERY_VALID_IP_USER = "get ip for user = \"Eduard Petrovich Morozko\"";
    private static final String QUERY_VALID_IP_EVENT = "get ip for event = \"SOLVE_TASK\"";
    private static final String QUERY_VALID_IP_STATUS = "get ip for status = \"FAILED\"";
    private static final String QUERY_VALID_IP_DATE = "get ip for date = \"30.08.2012 16:08:40\"";
    private static final String QUERY_VALID_USER_IP = "get user for ip = \"127.0.0.1\"";
    private static final String QUERY_VALID_USER_USER = "get user for user = \"Amigo\"";
    private static final String QUERY_VALID_USER_DATE = "get user for date = \"11.12.2013 10:11:12\"";
    private static final String QUERY_VALID_USER_EVENT = "get user for event = \"DONE_TASK\"";
    private static final String QUERY_VALID_USER_STATUS = "get user for status = \"ERROR\"";
    private static final String QUERY_VALID_DATE_DATE = "get date for date = \"03.01.2014 03:45:23\"";
    private static final String QUERY_VALID_DATE_IP = "get date for ip = \"127.0.0.1\"";
    private static final String QUERY_VALID_DATE_USER = "get date for user = \"Amigo\"";
    private static final String QUERY_VALID_DATE_EVENT = "get date for event = \"DOWNLOAD_PLUGIN\"";
    private static final String QUERY_VALID_DATE_STATUS = "get date for status = \"FAILED\"";
    private static final String QUERY_VALID_EVENT_IP = "get event for ip = \"146.34.15.5\"";
    private static final String QUERY_VALID_EVENT_USER = "get event for user = \"Amigo\"";
    private static final String QUERY_VALID_EVENT_DATE = "get event for date = \"30.01.2014 12:56:22\"";
    private static final String QUERY_VALID_EVENT_EVENT = "get event for event = \"WRITE_MESSAGE\"";
    private static final String QUERY_VALID_EVENT_STATUS = "get event for status = \"FAILED\"";
    private static final String QUERY_VALID_STATUS_IP = "get status for ip = \"192.168.100.2\"";
    private static final String QUERY_VALID_STATUS_USER = "get status for user = \"Eduard Petrovich Morozko\"";
    private static final String QUERY_VALID_STATUS_DATE = "get status for date = \"30.01.2014 12:56:22\"";
    private static final String QUERY_VALID_STATUS_EVENT = "get status for event = \"WRITE_MESSAGE\"";
    private static final String QUERY_VALID_STATUS_STATUS = "get status for status = \"OK\"";
    private static final String QUERY_VALID_IP_USER_11_12_2013__03_01_2014 = "get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"";
    private static final String QUERY_VALID_IP_DATE_11_12_2013__03_01_2014 = "get ip for date = \"12.12.2013 21:56:30\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"";
    private static final String QUERY_VALID_IP_EVENT_11_12_2013__03_01_2014 = "get ip for event = \"WRITE_MESSAGE\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"";
    private static final String QUERY_VALID_IP_STATUS_11_12_2013__03_01_2014 = "get ip for status = \"FAILED\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"";
    private static final String QUERY_VALID_DATE_EVENT_11_12_2013__03_01_2014 = "get date for event = \"LOGIN\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"";

    private static final String DATE_30_08_2012_16_08_13 = "30.08.2012 16:08:13";
    private static final String DATE_30_08_2012_16_08_40 = "30.08.2012 16:08:40";
    private static final String DATE_13_09_2013_5_04_50 = "13.09.2013 5:04:50";
    private static final String DATE_11_12_2013_10_11_12 = "11.12.2013 10:11:12";
    private static final String DATE_12_12_2013_21_56_30 = "12.12.2013 21:56:30";
    private static final String DATE_03_01_2014_03_45_23 = "03.01.2014 03:45:23";
    private static final String DATE_30_01_2014_12_56_22 = "30.01.2014 12:56:22";
    private static final String DATE_14_11_2015_07_08_01 = "14.11.2015 07:08:01";
    private static final String DATE_19_03_2016_00_00_00 = "19.03.2016 00:00:00";
    private static final String DATE_05_01_2021_20_22_55 = "05.01.2021 20:22:55";
    private static final String DATE_14_10_2021_11_38_21 = "14.10.2021 11:38:21";
    private static final String DATE_21_10_2021_19_45_25 = "21.10.2021 19:45:25";
    private static final String DATE_29_2_2028_5_4_7 = "29.2.2028 5:4:7";

    private static final String USER_EPM = "Eduard Petrovich Morozko";
    private static final String USER_VP = "Vasya Pupkin";
    private static final String USER_A = "Amigo";

    private static final String IP_120 = "120.120.120.122";
    private static final String IP_192 = "192.168.100.2";
    private static final String IP_146 = "146.34.15.5";
    private static final String IP_12 = "12.12.12.12";
    private static final String IP_127 = "127.0.0.1";

    private Date afterNull = null;
    private Date beforeNull = null;
    private Date date_30_08_2012_16_08_13;
    private Date date_30_08_2012_16_08_40;
    private Date date_13_09_2013_5_04_50;
    private Date date_11_12_2013_10_11_12;
    private Date date_12_12_2013_21_56_30;
    private Date date_03_01_2014_03_45_23;
    private Date date_30_01_2014_12_56_22;
    private Date date_14_11_2015_07_08_01;
    private Date date_19_03_2016_00_00_00;
    private Date date_05_01_2021_20_22_55;
    private Date date_14_10_2021_11_38_21;
    private Date date_21_10_2021_19_45_25;
    private Date date_29_2_2028_5_4_7;

    @Before
    public void beforeMethod() {
        String path = "/Users/iliashebanov/Documents/Java/JavaRush/JavaRushHomeWork/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task39/task3913/logs/";
        Path path1 = Paths.get(path);
        this.logParser = new LogParser(path1);

        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        try {
            this.date_30_08_2012_16_08_13 = formatForDateNow.parse(DATE_30_08_2012_16_08_13);
            this.date_30_08_2012_16_08_40 = formatForDateNow.parse(DATE_30_08_2012_16_08_40);
            this.date_13_09_2013_5_04_50 = formatForDateNow.parse(DATE_13_09_2013_5_04_50);
            this.date_11_12_2013_10_11_12 = formatForDateNow.parse(DATE_11_12_2013_10_11_12);
            this.date_12_12_2013_21_56_30 = formatForDateNow.parse(DATE_12_12_2013_21_56_30);
            this.date_03_01_2014_03_45_23 = formatForDateNow.parse(DATE_03_01_2014_03_45_23);
            this.date_30_01_2014_12_56_22 = formatForDateNow.parse(DATE_30_01_2014_12_56_22);
            this.date_14_11_2015_07_08_01 = formatForDateNow.parse(DATE_14_11_2015_07_08_01);
            this.date_19_03_2016_00_00_00 = formatForDateNow.parse(DATE_19_03_2016_00_00_00);
            this.date_05_01_2021_20_22_55 = formatForDateNow.parse(DATE_05_01_2021_20_22_55);
            this.date_14_10_2021_11_38_21 = formatForDateNow.parse(DATE_14_10_2021_11_38_21);
            this.date_21_10_2021_19_45_25 = formatForDateNow.parse(DATE_21_10_2021_19_45_25);
            this.date_29_2_2028_5_4_7 = formatForDateNow.parse(DATE_29_2_2028_5_4_7);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getNumberOfUniqueIPs() {
        int currentResult = logParser.getNumberOfUniqueIPs(date_30_08_2012_16_08_40, date_19_03_2016_00_00_00);
        int correctResult = 3;
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getNumberOfUniqueIPs_DATES_NULL() {
        int currentResult = logParser.getNumberOfUniqueIPs(afterNull, beforeNull);
        int correctResult = 5;
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getUniqueIPs() {
        Set<String> currentResult = logParser.getUniqueIPs(date_30_08_2012_16_08_40, date_19_03_2016_00_00_00);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(IP_192);
        correctResult.add(IP_146);
        correctResult.add(IP_127);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getUniqueIPs_NO_NULL() {
        Set<String> currentResult = logParser.getUniqueIPs(date_30_08_2012_16_08_40, date_19_03_2016_00_00_00);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getUniqueIPs_DATES_NULL() {
        Set<String> currentResult = logParser.getUniqueIPs(afterNull, beforeNull);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(IP_192);
        correctResult.add(IP_146);
        correctResult.add(IP_127);
        correctResult.add(IP_120);
        correctResult.add(IP_12);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getIPsForUser() {
        Set<String> currentResult = logParser.getIPsForUser(USER_EPM, date_30_08_2012_16_08_40, date_19_03_2016_00_00_00);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(IP_146);
        correctResult.add(IP_127);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getIPsForUser_NO_NULL() {
        Set<String> currentResult = logParser.getIPsForUser(USER_EPM, date_30_08_2012_16_08_40, date_19_03_2016_00_00_00);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getIPsForUser_DATES_NULL() {
        Set<String> currentResult = logParser.getIPsForUser(USER_VP, afterNull, beforeNull);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(IP_192);
        correctResult.add(IP_127);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getIPsForEvent() {
        Set<String> currentResult = logParser.getIPsForEvent(Event.WRITE_MESSAGE, date_30_08_2012_16_08_40, date_19_03_2016_00_00_00);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(IP_146);
        correctResult.add(IP_127);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getIPsForEvent_NO_NULL() {
        Set<String> currentResult = logParser.getIPsForEvent(Event.WRITE_MESSAGE, date_30_08_2012_16_08_40, date_19_03_2016_00_00_00);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getIPsForEvent_DATES_NULL() {
        Set<String> currentResult = logParser.getIPsForEvent(Event.SOLVE_TASK, date_30_08_2012_16_08_40, date_19_03_2016_00_00_00);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(IP_192);
        Assert.assertEquals(correctResult, currentResult);
    }


    @Test
    public void getIPsForStatus() {
        Set<String> currentResult = logParser.getIPsForStatus(Status.OK, date_30_08_2012_16_08_40, date_19_03_2016_00_00_00);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(IP_146);
        correctResult.add(IP_127);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getIPsForStatus_NO_NULL() {
        Set<String> currentResult = logParser.getIPsForStatus(Status.OK, date_30_08_2012_16_08_40, date_19_03_2016_00_00_00);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getIPsForStatus_DATES_NULL() {
        Set<String> currentResult = logParser.getIPsForStatus(Status.ERROR, date_30_08_2012_16_08_40, date_19_03_2016_00_00_00);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(IP_192);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getAllUsers() {
        Set<String> currentResult = logParser.getAllUsers();
        Set<String> correctResult = new HashSet<>();
        correctResult.add(USER_A);
        correctResult.add(USER_VP);
        correctResult.add(USER_EPM);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getAllUsers_NO_NULL() {
        Set<String> currentResult = logParser.getAllUsers();
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getNumberOfUsers() {
        int currentResult = logParser.getNumberOfUsers(afterNull, beforeNull);
        int correctResult = 3;
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getNumberOfUserEvents() {
        int currentResult = logParser.getNumberOfUserEvents(USER_VP, afterNull, beforeNull);
        int correctResult = 4;
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getUsersForIP() {
        Set<String> currentResult = logParser.getUsersForIP(IP_127, afterNull, beforeNull);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(USER_A);
        correctResult.add(USER_VP);
        correctResult.add(USER_EPM);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getUsersForIP_NO_NULL() {
        Set<String> currentResult = logParser.getUsersForIP(IP_127, afterNull, beforeNull);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getLoggedUsers() {
        Set<String> currentResult = logParser.getLoggedUsers(afterNull, beforeNull);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(USER_A);
        correctResult.add(USER_VP);
        correctResult.add(USER_EPM);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getLoggedUsers_NO_NULL() {
        Set<String> currentResult = logParser.getLoggedUsers(afterNull, beforeNull);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getDownloadedPluginUsers() {
        Set<String> currentResult = logParser.getDownloadedPluginUsers(afterNull, beforeNull);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(USER_EPM);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getDownloadedPluginUsers_NO_NULL() {
        Set<String> currentResult = logParser.getDownloadedPluginUsers(afterNull, beforeNull);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getWroteMessageUsers() {
        Set<String> currentResult = logParser.getWroteMessageUsers(afterNull, beforeNull);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(USER_EPM);
        correctResult.add(USER_VP);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getWroteMessageUsers_NO_NULL() {
        Set<String> currentResult = logParser.getWroteMessageUsers(afterNull, beforeNull);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getSolvedTaskUsers() {
        Set<String> currentResult = logParser.getSolvedTaskUsers(afterNull, beforeNull);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(USER_A);
        correctResult.add(USER_VP);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getSolvedTaskUsers_NO_NULL() {
        Set<String> currentResult = logParser.getSolvedTaskUsers(afterNull, beforeNull);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getSolvedTaskUsers_TASK() {
        Set<String> currentResult = logParser.getSolvedTaskUsers(afterNull, beforeNull, 18);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(USER_A);
        correctResult.add(USER_VP);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getSolvedTaskUsers_TASK_NO_NULL() {
        Set<String> currentResult = logParser.getSolvedTaskUsers(afterNull, beforeNull, 18);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getDoneTaskUsers() {
        Set<String> currentResult = logParser.getDoneTaskUsers(afterNull, beforeNull);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(USER_VP);
        correctResult.add(USER_EPM);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getDoneTaskUsers_NO_NULL() {
        Set<String> currentResult = logParser.getDoneTaskUsers(afterNull, beforeNull);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getDoneTaskUsers_TASK() {
        Set<String> currentResult = logParser.getDoneTaskUsers(afterNull, beforeNull, 15);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(USER_VP);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getDoneTaskUsers_TASK_NO_NULL() {
        Set<String> currentResult = logParser.getDoneTaskUsers(afterNull, beforeNull, 15);
        Assert.assertNotNull(currentResult);
    }


    @Test
    public void getDatesForUserAndEvent() {
        Set<Date> currentResult = logParser.getDatesForUserAndEvent("Amigo", Event.SOLVE_TASK, afterNull, beforeNull);
        Set<Date> correctResult = new HashSet<>();
        correctResult.add(date_21_10_2021_19_45_25);
        correctResult.add(date_29_2_2028_5_4_7);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getDatesForUserAndEvent_NO_NULL() {
        Set<Date> currentResult = logParser.getDatesForUserAndEvent("Amigo", Event.SOLVE_TASK, afterNull, beforeNull);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getDatesWhenSomethingFailed() {
        Set<Date> currentResult = logParser.getDatesWhenSomethingFailed(afterNull, beforeNull);
        Set<Date> correctResult = new HashSet<>();
        correctResult.add(date_05_01_2021_20_22_55);
        correctResult.add(date_11_12_2013_10_11_12);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getDatesWhenSomethingFailed_NO_NULL() {
        Set<Date> currentResult = logParser.getDatesWhenSomethingFailed(afterNull, beforeNull);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getDatesWhenErrorHappened() {
        Set<Date> currentResult = logParser.getDatesWhenErrorHappened(afterNull, beforeNull);
        Set<Date> correctResult = new HashSet<>();
        correctResult.add(date_30_01_2014_12_56_22);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getDatesWhenErrorHappened_NO_NULL() {
        Set<Date> currentResult = logParser.getDatesWhenErrorHappened(afterNull, beforeNull);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getDateWhenUserLoggedFirstTime() {
        Date currentResult = logParser.getDateWhenUserLoggedFirstTime(USER_VP, afterNull, beforeNull);
        Date correctResult = date_14_10_2021_11_38_21;
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getDateWhenUserLoggedFirstTime_NO_NULL() {
        Date currentResult = logParser.getDateWhenUserLoggedFirstTime(USER_VP, afterNull, beforeNull);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getDateWhenUserLoggedFirstTime_NULL() {
        Date currentResult = logParser.getDateWhenUserLoggedFirstTime(USER_VP, afterNull, date_11_12_2013_10_11_12);
        Assert.assertNull(currentResult);
    }

    @Test
    public void getDateWhenUserSolvedTask() {
        Date currentResult = logParser.getDateWhenUserSolvedTask(USER_VP, 18, afterNull, beforeNull);
        Date correctResult = date_30_01_2014_12_56_22;
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getDateWhenUserSolvedTask_NO_NULL() {
        Date currentResult = logParser.getDateWhenUserSolvedTask(USER_VP, 18, afterNull, beforeNull);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getDateWhenUserSolvedTask_NULL() {
        Date currentResult = logParser.getDateWhenUserSolvedTask(USER_VP, 18, afterNull, date_11_12_2013_10_11_12);
        Assert.assertNull(currentResult);
    }

    @Test
    public void getDateWhenUserDoneTask() {
        Date currentResult = logParser.getDateWhenUserDoneTask(USER_VP, 15, afterNull, beforeNull);
        Date correctResult = date_30_08_2012_16_08_40;
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getDateWhenUserDoneTask_NO_NULL() {
        Date currentResult = logParser.getDateWhenUserDoneTask(USER_VP, 15, afterNull, beforeNull);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getDateWhenUserDoneTask_NULL() {
        Date currentResult = logParser.getDateWhenUserDoneTask(USER_VP, 15, date_11_12_2013_10_11_12, beforeNull);
        Assert.assertNull(currentResult);
    }

    @Test
    public void getDatesWhenUserWroteMessage() {
        Set<Date> currentResult = logParser.getDatesWhenUserWroteMessage(USER_EPM, afterNull, beforeNull);
        Set<Date> correctResult = new HashSet<>();
        correctResult.add(date_11_12_2013_10_11_12);
        correctResult.add(date_12_12_2013_21_56_30);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getDatesWhenUserWroteMessage_NO_NULL() {
        Set<Date> currentResult = logParser.getDatesWhenUserWroteMessage(USER_EPM, afterNull, beforeNull);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getDatesWhenUserDownloadedPlugin() {
        Set<Date> currentResult = logParser.getDatesWhenUserDownloadedPlugin(USER_EPM, afterNull, beforeNull);
        Set<Date> correctResult = new HashSet<>();
        correctResult.add(date_13_09_2013_5_04_50);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getDatesWhenUserDownloadedPlugin_NO_NULL() {
        Set<Date> currentResult = logParser.getDatesWhenUserDownloadedPlugin(USER_EPM, afterNull, beforeNull);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getNumberOfAllEvents() {
        int currentResult = logParser.getNumberOfAllEvents(date_30_08_2012_16_08_40, date_12_12_2013_21_56_30);
        int correctResult = 2;
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getAllEvents() {
        Set<Event> currentResult = logParser.getAllEvents(date_30_08_2012_16_08_40, date_12_12_2013_21_56_30);
        Set<Event> correctResult = new HashSet<>();
        correctResult.add(Event.WRITE_MESSAGE);
        correctResult.add(Event.DOWNLOAD_PLUGIN);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getAllEvents_NO_NULL() {
        Set<Event> currentResult = logParser.getAllEvents(date_30_08_2012_16_08_40, date_12_12_2013_21_56_30);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getEventsForIP() {
        Set<Event> currentResult = logParser.getEventsForIP(IP_146, date_30_08_2012_16_08_40, date_12_12_2013_21_56_30);
        Set<Event> correctResult = new HashSet<>();
        correctResult.add(Event.DOWNLOAD_PLUGIN);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getEventsForIP_NO_NULL() {
        Set<Event> currentResult = logParser.getEventsForIP(IP_146, date_30_08_2012_16_08_40, date_12_12_2013_21_56_30);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getEventsForUser() {
        Set<Event> currentResult = logParser.getEventsForUser(USER_EPM, date_30_08_2012_16_08_40, date_12_12_2013_21_56_30);
        Set<Event> correctResult = new HashSet<>();
        correctResult.add(Event.WRITE_MESSAGE);
        correctResult.add(Event.DOWNLOAD_PLUGIN);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getEventsForUser_NO_NULL() {
        Set<Event> currentResult = logParser.getEventsForUser(USER_EPM, date_30_08_2012_16_08_40, date_12_12_2013_21_56_30);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getFailedEvents() {
        Set<Event> currentResult = logParser.getFailedEvents(date_30_08_2012_16_08_40, date_12_12_2013_21_56_30);
        Set<Event> correctResult = new HashSet<>();
        correctResult.add(Event.WRITE_MESSAGE);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getFailedEvents_NO_NULL() {
        Set<Event> currentResult = logParser.getFailedEvents(date_30_08_2012_16_08_40, date_12_12_2013_21_56_30);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getErrorEvents() {
        Set<Event> currentResult = logParser.getErrorEvents(date_30_08_2012_16_08_40, date_12_12_2013_21_56_30);
        Set<Event> correctResult = new HashSet<>();
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getErrorEvents_NO_NULL() {
        Set<Event> currentResult = logParser.getErrorEvents(date_30_08_2012_16_08_40, date_12_12_2013_21_56_30);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getNumberOfAttemptToSolveTask() {
        int currentResult = logParser.getNumberOfAttemptToSolveTask(18, afterNull, beforeNull);
        int correctResult = 3;
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getNumberOfSuccessfulAttemptToSolveTask() {
        int currentResult = logParser.getNumberOfSuccessfulAttemptToSolveTask(15, afterNull, beforeNull);
        int correctResult = 1;
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getAllSolvedTasksAndTheirNumber() {
        Map<Integer, Integer> currentResult = logParser.getAllSolvedTasksAndTheirNumber(afterNull, beforeNull);
        Map<Integer, Integer> correctResult = new HashMap<>();
        correctResult.put(1, 1);
        correctResult.put(18, 3);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getAllSolvedTasksAndTheirNumber_NO_NULL() {
        Map<Integer, Integer> currentResult = logParser.getAllSolvedTasksAndTheirNumber(afterNull, beforeNull);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void getAllDoneTasksAndTheirNumber() {
        Map<Integer, Integer> currentResult = logParser.getAllDoneTasksAndTheirNumber(afterNull, beforeNull);
        Map<Integer, Integer> correctResult = new HashMap<>();
        correctResult.put(48, 1);
        correctResult.put(15, 1);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void getAllDoneTasksAndTheirNumber_NO_NULL() {
        Map<Integer, Integer> currentResult = logParser.getAllDoneTasksAndTheirNumber(afterNull, beforeNull);
        Assert.assertNotNull(currentResult);
    }

    @Test
    public void executeAllIp() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_IP);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(IP_120);
        correctResult.add(IP_146);
        correctResult.add(IP_192);
        correctResult.add(IP_12);
        correctResult.add(IP_127);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeAllUser() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_USER);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(USER_A);
        correctResult.add(USER_VP);
        correctResult.add(USER_EPM);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeAllDate() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_DATE);
        Set<Date> correctResult = new HashSet<>();
        correctResult.add(date_30_08_2012_16_08_13);
        correctResult.add(date_30_08_2012_16_08_40);
        correctResult.add(date_13_09_2013_5_04_50);
        correctResult.add(date_11_12_2013_10_11_12);
        correctResult.add(date_12_12_2013_21_56_30);
        correctResult.add(date_03_01_2014_03_45_23);
        correctResult.add(date_30_01_2014_12_56_22);
        correctResult.add(date_14_11_2015_07_08_01);
        correctResult.add(date_19_03_2016_00_00_00);
        correctResult.add(date_05_01_2021_20_22_55);
        correctResult.add(date_14_10_2021_11_38_21);
        correctResult.add(date_21_10_2021_19_45_25);
        correctResult.add(date_29_2_2028_5_4_7);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeAllEvent() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_EVENT);
        Set<Event> correctResult = new HashSet<>();
        correctResult.add(Event.DONE_TASK);
        correctResult.add(Event.WRITE_MESSAGE);
        correctResult.add(Event.LOGIN);
        correctResult.add(Event.DOWNLOAD_PLUGIN);
        correctResult.add(Event.SOLVE_TASK);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeAllStatus() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_STATUS);
        Set<Status> correctResult = new HashSet<>();
        correctResult.add(Status.OK);
        correctResult.add(Status.ERROR);
        correctResult.add(Status.FAILED);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void execute_1_NULL() {
        Set<Object> currentResult = logParser.execute(QUERY_INVALID_1);
        Assert.assertNull(currentResult);
    }

    @Test
    public void execute_2_NULL() {
        Set<Object> currentResult = logParser.execute(QUERY_INVALID_2);
        Assert.assertNull(currentResult);
    }

    @Test
    public void execute_3_NULL() {
        Set<Object> currentResult = logParser.execute(QUERY_INVALID_3);
        Assert.assertNull(currentResult);
    }

    @Test
    public void execute_4_NULL() {
        Set<Object> currentResult = logParser.execute(QUERY_INVALID_4);
        Assert.assertNull(currentResult);
    }

    @Test
    public void executeGetIpsForIPAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_IP_IP);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(IP_127);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetIpsForUserAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_IP_USER);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(IP_127);
        correctResult.add(IP_146);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetIpsForEventAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_IP_EVENT);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(IP_120);
        correctResult.add(IP_12);
        correctResult.add(IP_192);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetIpsForStatusAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_IP_STATUS);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(IP_146);
        correctResult.add(IP_127);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetIpsForDateAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_IP_DATE);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(IP_192);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetUsersForIpAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_USER_IP);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(USER_A);
        correctResult.add(USER_VP);
        correctResult.add(USER_EPM);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetUsersForUserAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_USER_USER);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(USER_A);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetUsersForDateAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_USER_DATE);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(USER_EPM);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetUsersForEventAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_USER_EVENT);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(USER_EPM);
        correctResult.add(USER_VP);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetUsersForStatusAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_USER_STATUS);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(USER_VP);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetDateForIpAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_DATE_IP);
        Set<Date> correctResult = new HashSet<>();
        correctResult.add(date_14_11_2015_07_08_01);
        correctResult.add(date_11_12_2013_10_11_12);
        correctResult.add(date_14_10_2021_11_38_21);
        correctResult.add(date_30_08_2012_16_08_13);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetDateForDateAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_DATE_DATE);
        Set<Date> correctResult = new HashSet<>();
        correctResult.add(date_03_01_2014_03_45_23);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetDateForUserAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_DATE_USER);
        Set<Date> correctResult = new HashSet<>();
        correctResult.add(date_29_2_2028_5_4_7);
        correctResult.add(date_21_10_2021_19_45_25);
        correctResult.add(date_30_08_2012_16_08_13);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetDateForEventAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_DATE_EVENT);
        Set<Date> correctResult = new HashSet<>();
        correctResult.add(date_13_09_2013_5_04_50);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetDateForStatusAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_DATE_STATUS);
        Set<Date> correctResult = new HashSet<>();
        correctResult.add(date_11_12_2013_10_11_12);
        correctResult.add(date_05_01_2021_20_22_55);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetEventForIpAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_EVENT_IP);
        Set<Event> correctResult = new HashSet<>();
        correctResult.add(Event.DOWNLOAD_PLUGIN);
        correctResult.add(Event.WRITE_MESSAGE);
        correctResult.add(Event.LOGIN);
        correctResult.add(Event.DONE_TASK);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetEventForUserAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_EVENT_USER);
        Set<Event> correctResult = new HashSet<>();
        correctResult.add(Event.LOGIN);
        correctResult.add(Event.SOLVE_TASK);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetEventForDateAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_EVENT_DATE);
        Set<Event> correctResult = new HashSet<>();
        correctResult.add(Event.SOLVE_TASK);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetEventForEventAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_EVENT_EVENT);
        Set<Event> correctResult = new HashSet<>();
        correctResult.add(Event.WRITE_MESSAGE);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetEventForStatusAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_EVENT_STATUS);
        Set<Event> correctResult = new HashSet<>();
        correctResult.add(Event.WRITE_MESSAGE);
        correctResult.add(Event.DONE_TASK);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetStatusForIpAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_STATUS_IP);
        Set<Status> correctResult = new HashSet<>();
        correctResult.add(Status.ERROR);
        correctResult.add(Status.OK);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetStatusForUserAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_STATUS_USER);
        Set<Status> correctResult = new HashSet<>();
        correctResult.add(Status.FAILED);
        correctResult.add(Status.OK);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetStatusForDateAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_STATUS_DATE);
        Set<Status> correctResult = new HashSet<>();
        correctResult.add(Status.ERROR);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetStatusForEventAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_STATUS_EVENT);
        Set<Status> correctResult = new HashSet<>();
        correctResult.add(Status.FAILED);
        correctResult.add(Status.OK);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetStatusForStatusAndValue() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_STATUS_STATUS);
        Set<Status> correctResult = new HashSet<>();
        correctResult.add(Status.OK);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetIpForUserAndValueAndAfterBefor() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_IP_USER_11_12_2013__03_01_2014);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(IP_127);
        correctResult.add(IP_146);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetIpForDateAndValueAndAfterBefor() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_IP_DATE_11_12_2013__03_01_2014);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(IP_146);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetIpForEventAndValueAndAfterBefor() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_IP_EVENT_11_12_2013__03_01_2014);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(IP_127);
        correctResult.add(IP_146);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetIpForStatusAndValueAndAfterBefor() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_IP_STATUS_11_12_2013__03_01_2014);
        Set<String> correctResult = new HashSet<>();
        correctResult.add(IP_127);
        Assert.assertEquals(correctResult, currentResult);
    }

    @Test
    public void executeGetDateForEventAndValueAndAfterBefor() {
        Set<Object> currentResult = logParser.execute(QUERY_VALID_DATE_EVENT_11_12_2013__03_01_2014);
        Set<Date> correctResult = new HashSet<>();
        correctResult.add(date_03_01_2014_03_45_23);
        Assert.assertEquals(correctResult, currentResult);
    }
}