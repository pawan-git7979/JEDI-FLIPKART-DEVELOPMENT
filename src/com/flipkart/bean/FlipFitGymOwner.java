package com.flipkart.bean;

import java.util.List;

public class FlipFitGymOwner extends User {
    private List<String> gymNames;
    private String adhaarNumber;
    private String panNumber;
    private String governmentDocument;

    public FlipFitGymOwner(String id, String name, String email, String password, String address,
                           List<String> gymNames, String adhaarNumber, String panNumber, String governmentDocument) {
        super(id, name, email, password, "Gym Owner", address);
        this.gymNames = gymNames;
        this.adhaarNumber = adhaarNumber;
        this.panNumber = panNumber;
        this.governmentDocument = governmentDocument;
    }

    public void enrollGym() {
        // Logic to enroll in a gym
    }

    public void addSlot() {
        // Logic to add slots
    }

    public void deleteSlot() {
        // Logic to delete slots
    }

    public void updateSlot() {
        // Logic to update slots
    }

    // Getters and Setters
}
