package com.flipkart.bean;

import java.util.List;

public class GymCenter {
    private String id;
    private String name;
    private String location;
    private String adminId;
    private List<String> slotIds;

    public GymCenter(String id, String name, String location, String adminId, List<String> slotIds) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.adminId = adminId;
        this.slotIds = slotIds;
    }

    public void getGymDetails() {
        // Logic to fetch gym details
    }

    // Getters and Setters
}
