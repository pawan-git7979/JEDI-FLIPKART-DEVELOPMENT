package com.flipkart.beans;

import java.util.Queue;

public class Slot {
    private String slotId;
    private String date;
    private String startTime;
    private String endTime;
    private int availableSeats;
    private Queue<String> waitingBookingIds;

    public Slot(String slotId, String date, String startTime, String endTime, int availableSeats, Queue<String> waitingBookingIds) {
        this.slotId = slotId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.availableSeats = availableSeats;
        this.waitingBookingIds = waitingBookingIds;
    }

    public void getTransactionDetail() {
        // Logic for transaction details
    }

    public void setTransactionDetail() {
        // Logic to set transaction details
    }

    public void setWaitlist() {
        // Logic to set waitlist
    }

    public void getWaitlist() {
        // Logic to get waitlist
    }

    public void updateWaitlist() {
        // Logic to update waitlist
    }

    // Getters and Setters
}
