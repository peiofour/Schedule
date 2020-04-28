package com.pierrefournier.schedule.model;

public class Child extends User {

    private Planning planning;

    protected Child(String firstName, String lastName, String pseudo, String password) {

        super(firstName, lastName, pseudo, password);
        this.planning = new Planning();
    }
}
