package com.pierrefournier.schedule.model;

import java.util.ArrayList;
import java.util.List;

public class Child extends User {

    private final List<Task> taskList;

    public Child(String firstName, String lastName, String pseudo, String password, String phone) {

        super(firstName, lastName, pseudo, password, phone, false);
        taskList = new ArrayList<Task>();
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
