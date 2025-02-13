package com.flipkart.beans;

public class BookingDetails {
    private String bookingId;
    private String userId;
    private String gymCenterId;
    private String slotId;
    private String bookingStatus;

    public BookingDetails(String bookingId, String userId, String gymCenterId, String slotId, String bookingStatus) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.gymCenterId = gymCenterId;
        this.slotId = slotId;
        this.bookingStatus = bookingStatus;
    }

    public void getStatus() {
        // Logic to get booking status
    }

    public void getPaymentDetails() {
        // Logic to get payment details
    }

    // Getters and Setters
}
