package com.flipkart.bean;

import java.util.List;

public class FlipFitAdmin extends User {
    private List<String> gymNames;

    public FlipFitAdmin(String id, String name, String email, String password, String address, List<String> gymNames) {
        super(id, name, email, password, "Gym Admin", address);
        this.gymNames = gymNames;
    }

    public void gymRequestProposal() {
        // Logic for gym approval requests
    }

    // Getters and Setters
}
