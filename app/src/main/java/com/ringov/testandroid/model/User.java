package com.ringov.testandroid.model;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private boolean online;

    public User(){}

    public User(int id, String firstName, String lastName, boolean online){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.online = online;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isOnline() {
        return online;
    }
}
