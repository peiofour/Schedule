package com.pierrefournier.schedule.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.*;

public class Parent extends User {

    private List<Child> children;

    public Parent(String firstName, String lastName, String pseudo, String password) {
        super(firstName, lastName, pseudo, password);
        this.children = new ArrayList<Child>();
    }

    public List<Child> getChildren() {
        return children;
    }

    public void addChild(Child child){
        this.children.add(child);
    }

    public void removeChild(Child child){
        this.children.remove(child);
    }
}
