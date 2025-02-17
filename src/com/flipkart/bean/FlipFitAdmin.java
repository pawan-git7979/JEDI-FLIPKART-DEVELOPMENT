package com.flipkart.bean;

import java.util.List;

public class FlipFitAdmin extends FlipFitUser {
    private List<String> gymRequests;

    // Default Constructor
    public FlipFitAdmin() {
        super();
    }

    // Parameterized Constructor
    public FlipFitAdmin(int userId, String name, String email, String password, String address, List<String> gymRequests) {
        super(userId, name, email, password, FlipFitUser.ROLE_ADMIN, address);
        this.gymRequests = gymRequests;
    }

    // Getters and Setters
    public List<String> getGymRequests() { return gymRequests; }
    public void setGymRequests(List<String> gymRequests) { this.gymRequests = gymRequests; }

    @Override
    public String toString() {
        return "FlipFitAdmin { " + super.toString() + ", gymRequests=" + gymRequests + " }";
    }
}
