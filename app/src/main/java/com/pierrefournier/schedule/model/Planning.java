package com.pierrefournier.schedule.model;

import java.time.LocalDateTime;
import java.util.List;

public class Planning {

    private List<Task> taskList;

    public void createTask(String title, LocalDateTime dateTime, Integer recalls){
        Task task = new Task(title, dateTime, recalls);
        taskList.add(task);
    }

}
