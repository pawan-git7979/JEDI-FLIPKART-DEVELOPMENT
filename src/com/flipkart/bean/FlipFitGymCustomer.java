package com.flipkart.beans;

import java.util.List;

public class FlipFitGymCustomer extends User {
    private List<Integer> bookingSlotIds;
    private String governmentDocument;

    public FlipFitGymCustomer(String id, String name, String email, String password, String address,
                              List<Integer> bookingSlotIds, String governmentDocument) {
        super(id, name, email, password, "Gym Customer", address);
        this.bookingSlotIds = bookingSlotIds;
        this.governmentDocument = governmentDocument;
    }

    public void bookSlot() {
        // Logic to book a slot
    }

    public void viewHistory() {
        // Logic to view booking history
    }

    public void inWaitlist() {
        // Logic to manage waitlist
    }

    public void cancelBooking() {
        // Logic to cancel a booking
    }

    public void viewSlots() {
        // Logic to view slots
    }

    // Getters and Setters
}
