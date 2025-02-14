package com.flipkart.bean;

import java.util.List;

public class FlipFitGymCenter {
    private int id;
    private String name;
    private String location;
    private int adminId;  // or ownerId if you prefer
    private List<FlipFitGymSlot> slots; // Now holds slot objects

    public FlipFitGymCenter() {}

    public FlipFitGymCenter(int id, String name, String location, int adminId, List<FlipFitGymSlot> slots) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.adminId = adminId;
        this.slots = slots;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getAdminId() { return adminId; }
    public void setAdminId(int adminId) { this.adminId = adminId; }

    public List<FlipFitGymSlot> getSlots() { return slots; }
    public void setSlots(List<FlipFitGymSlot> slots) { this.slots = slots; }
}
