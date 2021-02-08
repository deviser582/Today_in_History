package com.example.today_in_history;

public class RvData {
    private String year;
    private String monthday;
    private String title;
    private String desc;
    private String type;

    public RvData(String year,String monthday,String type,String title,String desc){
        this.year = year;
        this.monthday = monthday;
        this.title = title;
        this.desc = desc;
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonthday() {
        return monthday;
    }

    public void setMonthday(String monthday) {
        this.monthday = monthday;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
