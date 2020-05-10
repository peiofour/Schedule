package com.pierrefournier.schedule.model;

import java.util.List;

public class Task {

    private String title;
    private String date;
    private String startHour;
    private String endHour;
    private String type;
    private Integer recalls;
    private String interval;
    private String comment;
    private List<String> recurringDays;

    public Task(String title, String date, String startHour, String endHour, String type, Integer recalls, String interval, String comment,
                List<String> recurringDays){
        this.title = title;
        this.date = date;
        this.startHour = startHour;
        this.endHour = endHour;
        this.type = type;
        this.recalls = recalls;
        this.interval = interval;
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

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
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

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }
}
