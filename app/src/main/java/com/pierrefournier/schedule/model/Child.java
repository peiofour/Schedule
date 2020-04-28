package com.pierrefournier.schedule.model;

public class Child extends User {

    private Planning planning;

    protected Child(String firstName, String lastName, String pseudo, String password, String phone) {

        super(firstName, lastName, pseudo, password, phone);
        this.planning = new Planning();
    }
}
