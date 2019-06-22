package com.example.asus.moodlog;

public class LogItem {
    private int id;
    private String logs;
    private String time;
    private String logtheme;

    public LogItem() {
        super();
        logs = "";
        time = "";
        logtheme="";
    }
    public LogItem(String logs, String time,String logtheme) {
        super();
        this.logs = logs;
        this.time = time;
        this.logtheme = logtheme;
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
    public String getLogtheme() {
        return logtheme;
    }

    public void setLogtheme(String logtheme) {
        this.logtheme = logtheme;
    }
}
