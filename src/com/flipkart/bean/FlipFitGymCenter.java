package com.flipkart.bean;

import java.util.List;

public class FlipFitGymCenter {
    private int id;
    private String name;
    private String location;
    private int adminId;
    private int ownerId;      // Added Gym Owner ID
    private String ownerName; // Added Gym Owner Name
    private List<FlipFitGymSlot> slots;

    public FlipFitGymCenter() {}

    public FlipFitGymCenter(int id, String name, String location, int adminId, int ownerId, List<FlipFitGymSlot> slots) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.adminId = adminId;
        this.ownerId = ownerId;
//        this.ownerName = ownerName;
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

    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public List<FlipFitGymSlot> getSlots() { return slots; }
    public void setSlots(List<FlipFitGymSlot> slots) { this.slots = slots; }

    @Override
    public String toString() {
        return "FlipFitGymCenter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", adminId=" + adminId +
                ", ownerId=" + ownerId +
                ", ownerName='" + ownerName + '\'' +
                ", slots=" + slots +
                '}';
    }
}
