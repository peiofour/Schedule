package com.pierrefournier.schedule.model;

import java.util.List;

public class Task {

    private String title;
    private String date;
    private String hour;
    private String duration;
    private String type;
    private Integer recalls;
    private String comment;
    private List<String> recurringDays;

    public Task(String title, String date, String hour, String duration, String type, Integer recalls, String comment, List<String> recurringDays){
        this.title = title;
        this.date = date;
        this.hour = hour;
        this.duration = duration;
        this.type = type;
        this.recalls = recalls;
        this.recurringDays = recurringDays;
        this.comment = comment;
    }

    public Boolean isRecurrent(){
        return !recurringDays.isEmpty();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRecalls() {
        return recalls;
    }

    public void setRecalls(Integer recalls) {
        this.recalls = recalls;
    }

    public void setRecurringDays(List<String> recurringDays) {
        this.recurringDays = recurringDays;
    }

    public List<String> getRecurringDays() {
        return recurringDays;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
