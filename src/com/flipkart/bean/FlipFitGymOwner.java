package com.flipkart.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * The FlipFitGymOwner class represents a gym owner in the FlipFit system.
 * It extends FlipFitUser and includes additional attributes such as gym names,
 * Aadhaar number, PAN number, and other government documents.
 */
public class FlipFitGymOwner extends FlipFitUser {
    private List<String> gymNames;
    private String aadhaarNumber;
    private String panNumber;
    private String governmentDocument;

    /**
     * Default Constructor.
     * Initializes a FlipFitGymOwner object with default values.
     */
    public FlipFitGymOwner() {
        super();
        this.gymNames = new ArrayList<>();
    }

    /**
     * Parameterized Constructor.
     * Initializes a FlipFitGymOwner object with the given details.
     *
     * @param userId              The unique ID of the gym owner.
     * @param name                The name of the gym owner.
     * @param email               The email address of the gym owner.
     * @param password            The password of the gym owner.
     * @param address             The address of the gym owner.
     * @param gymNames            The list of gyms owned by the user.
     * @param aadhaarNumber       The Aadhaar number for identity verification.
     * @param panNumber           The PAN number for tax purposes.
     * @param governmentDocument  Any additional government-issued document.
     */
    public FlipFitGymOwner(int userId, String name, String email, String password, String address,
                           List<String> gymNames, String aadhaarNumber, String panNumber,
                           String governmentDocument) {
        super(userId, name, email, password, FlipFitUser.ROLE_OWNER, address);
        this.gymNames = gymNames;
        this.aadhaarNumber = aadhaarNumber;
        this.panNumber = panNumber;
        this.governmentDocument = governmentDocument;
    }

    /**
     * Gets the list of gym names owned by the user.
     *
     * @return A list of gym names.
     */
    public List<String> getGymNames() {
        return gymNames;
    }

    /**
     * Sets the list of gym names owned by the user.
     *
     * @param gymNames A list of gym names.
     */
    public void setGymNames(List<String> gymNames) {
        this.gymNames = gymNames;
    }

    /**
     * Gets the Aadhaar number of the gym owner.
     *
     * @return The Aadhaar number.
     */
    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    /**
     * Sets the Aadhaar number of the gym owner.
     *
     * @param aadhaarNumber The Aadhaar number.
     */
    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    /**
     * Gets the PAN number of the gym owner.
     *
     * @return The PAN number.
     */
    public String getPanNumber() {
        return panNumber;
    }

    /**
     * Sets the PAN number of the gym owner.
     *
     * @param panNumber The PAN number.
     */
    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    /**
     * Gets the government-issued document of the gym owner.
     *
     * @return The government-issued document.
     */
    public String getGovernmentDocument() {
        return governmentDocument;
    }

    /**
     * Sets the government-issued document of the gym owner.
     *
     * @param governmentDocument The government-issued document.
     */
    public void setGovernmentDocument(String governmentDocument) {
        this.governmentDocument = governmentDocument;
    }

    /**
     * Returns a string representation of the FlipFitGymOwner object.
     *
     * @return A formatted string containing gym owner details.
     */
    @Override
    public String toString() {
        return "FlipFitGymOwner { " + super.toString() +
                ", gymNames=" + gymNames + ", aadhaarNumber='" + aadhaarNumber +
                "', panNumber='" + panNumber + "', governmentDocument='" + governmentDocument + "' }";
    }
}
