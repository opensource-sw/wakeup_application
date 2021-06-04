package com.example.wakeup.SQLite;

public class AlarmList {
    private String name;
    private Integer hour;
    private Integer minute;

    public AlarmList() {
    }

    public String getName() {
        return name;
    }
    public Integer getHour() {
        return hour;
    }
    public Integer getMinute() {
        return minute;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setHour(Integer hour) {
        this.hour = hour;
    }
    public void setMinute(Integer minute) {
        this.minute = minute;
    }
}
