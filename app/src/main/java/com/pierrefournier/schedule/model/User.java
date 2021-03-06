package com.pierrefournier.schedule.model;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private final Boolean isParent;

    protected User(String firstName, String lastName, String email, String password, String phone, Boolean isParent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.isParent = isParent;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }
}
