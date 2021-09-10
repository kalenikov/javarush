package com.javarush.task.task39.task3913;

import java.util.Date;

public class TaskLog {
    private String ip;
    private String username;
    private Date date = null;
    private Event event;
    private Integer task;
    private Status status;

    public TaskLog(String ip, String username, Date date, Event event, Integer task, Status status) {
        this.ip = ip;
        this.username = username;
        this.date = date;
        this.event = event;
        this.task = task;
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public String getUsername() {
        return username;
    }

    public Date getDate() {
        return date;
    }

    public Event getEvent() {
        return event;
    }

    public Integer getTask() {
        return task;
    }

    public Status getStatus() {
        return status;
    }
}
