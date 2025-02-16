package com.flipkart.bean;

import java.util.ArrayList;
import java.util.List;

public class FlipFitGymOwner extends FlipFitUser {
    private List<String> gymNames;
    private String aadhaarNumber;
    private String panNumber;
    private String governmentDocument;

    public FlipFitGymOwner() {
        super();
        this.gymNames = new ArrayList<>();
    }
//    public FlipFitGymOwner(int ownerId, String name, String email, String password, String owner, String address, List<String> gymNames, String aadhaarNumber, String panNumber, String governmentDocument) {
//
//    }

    public FlipFitGymOwner(int userId, String name, String email, String password, String address,
                           List<String> gymNames, String aadhaarNumber, String panNumber,
                           String governmentDocument) {
        super(userId, name, email, password, FlipFitUser.ROLE_OWNER, address);
        this.gymNames = gymNames;
        this.aadhaarNumber = aadhaarNumber;
        this.panNumber = panNumber;
        this.governmentDocument = governmentDocument;
    }



    // Getters and Setters
    public List<String> getGymNames() { return gymNames; }
    public void setGymNames(List<String> gymNames) { this.gymNames = gymNames; }

    public String getAadhaarNumber() { return aadhaarNumber; }
    public void setAadhaarNumber(String aadhaarNumber) { this.aadhaarNumber = aadhaarNumber; }

    public String getPanNumber() { return panNumber; }
    public void setPanNumber(String panNumber) { this.panNumber = panNumber; }

    public String getGovernmentDocument() { return governmentDocument; }
    public void setGovernmentDocument(String governmentDocument) { this.governmentDocument = governmentDocument; }

    @Override
    public String toString() {
        return "FlipFitGymOwner { " + super.toString() +
                ", gymNames=" + gymNames + ", aadhaarNumber='" + aadhaarNumber +
                "', panNumber='" + panNumber + "', governmentDocument='" + governmentDocument + "' }";
    }
}
