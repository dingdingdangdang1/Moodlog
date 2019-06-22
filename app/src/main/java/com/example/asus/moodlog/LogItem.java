package com.example.asus.moodlog;

public class LogItem {
    private int id;
    private String logs;
    private String time;

    public LogItem() {
        super();
        logs = "";
        time = "";
    }
    public LogItem(String logs, String time) {
        super();
        this.logs = logs;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogs() {
        return logs;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
