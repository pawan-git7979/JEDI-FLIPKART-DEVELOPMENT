package com.flipkart.bean;

public class User {
    protected String id;
    protected String name;
    protected String email;
    protected String password;
    protected String role;
    protected String address;

    public User(String id, String name, String email, String password, String role, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.address = address;
    }

    public void get() {
        // Implementation of getting user details
    }

    public void signup() {
        // Implementation of user signup
    }

    public void signin() {
        // Implementation of user login
    }

    // Getters and Setters
}
