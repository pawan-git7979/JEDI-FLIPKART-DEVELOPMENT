package com.flipkart.bean;

import java.util.List;

public class GymOwner extends User {
    // List of gym center IDs or actual GymCenter objects
    private List<Integer> gymCentersOwned;

    public GymOwner() {}
    public GymOwner(int id, String name, String email, String password, String role, 
                    List<Integer> gymCentersOwned) {
        super(id, name, email, password, role);
        this.gymCentersOwned = gymCentersOwned;
    }

    public List<Integer> getGymCentersOwned() { return gymCentersOwned; }
    public void setGymCentersOwned(List<Integer> gymCentersOwned) { this.gymCentersOwned = gymCentersOwned; }
}
