package com.javarush.task.task39.task3913;

import java.util.Date;

public class OneLog {
    private String ip;
    private String name;
    private Date date;
    private Event event;
    private int parameter;
    private Status status;

    public OneLog(String ip, String name, Date date, Event event, int parameter, Status status) {
        this.ip = ip;
        this.name = name;
        this.date = date;
        this.event = event;
        this.parameter = parameter;
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Event getEvent() {
        return event;
    }

    public int getParameter() {
        return parameter;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "OneLog{" +
                "ip='" + ip + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", event=" + event +
                ", parameter='" + parameter + '\'' +
                ", status=" + status +
                '}';
    }
}
