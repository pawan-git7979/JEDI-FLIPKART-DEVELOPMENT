package com.flipkart.bean;

import java.util.List;

public class FlipFitGymCustomer extends FlipFitUser {
    private List<Integer> bookingSlotIds;
    private String governmentDocumentNumber;

    // Default Constructor

    // Parameterized Constructor
    public FlipFitGymCustomer(int userId, String name, String email, String password, String address,
                              String governmentDocumentNumber) {
        super(userId, name, email, password, "GymCustomer", address);
        this.governmentDocumentNumber = governmentDocumentNumber;
    }

    // Getters and Setters
    public List<Integer> getBookingSlotIds() { return bookingSlotIds; }
    public void setBookingSlotIds(List<Integer> bookingSlotIds) { this.bookingSlotIds = bookingSlotIds; }

    public String getGovernmentDocumentNumber() { return governmentDocumentNumber; }
    public void setGovernmentDocumentNumber(String governmentDocumentNumber) { this.governmentDocumentNumber = governmentDocumentNumber; }

    @Override
    public String toString() {
        return "FlipFitGymCustomer { " + super.toString() +
                ", bookingSlotIds=" + bookingSlotIds +
                ", governmentDocumentNumber='" + governmentDocumentNumber + "' }";
    }
}
