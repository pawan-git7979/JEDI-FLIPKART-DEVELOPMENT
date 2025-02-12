package com.flipkart.bean;

import java.util.List;

public class GymCenter {
    private int id;
    private String name;
    private String location;    
    private List<Integer> slots; // List of Slot IDs (or actual Slot objects)

    public GymCenter() {}
    public GymCenter(int id, String name, String location, int adminId, List<Integer> slots) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.slots = slots;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public List<Integer> getSlots() { return slots; }
    public void setSlots(List<Integer> slots) { this.slots = slots; }
}
