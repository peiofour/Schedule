package com.pierrefournier.schedule.model;

public class Child extends User {

    private Planning planning;

    public Child(String firstName, String lastName, String pseudo, String password, String phone) {

        super(firstName, lastName, pseudo, password, phone, false);
        this.planning = new Planning();
    }
}
