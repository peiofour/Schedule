package com.pierrefournier.schedule.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Task {

    private String title;

    private LocalDateTime dateTime;

    private Integer recalls;

    public Task(String title, LocalDateTime dateTime, Integer recalls) {
        this.title = title;
        this.dateTime = dateTime;
        this.recalls = recalls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getRecalls() {
        return recalls;
    }

    public void setRecalls(Integer recalls) {
        this.recalls = recalls;
    }
}
