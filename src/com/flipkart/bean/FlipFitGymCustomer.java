package com.flipkart.bean;

import java.util.List;

public class FlipFitGymCustomer extends FlipFitUser {
    private List<Integer> bookings; // List of booking IDs

    public FlipFitGymCustomer() {}

    public FlipFitGymCustomer(int userId, String name, String email, String password, String membershipType) {
        super(userId, name, email, password, "GymCustomer");
        // membershipType is not stored here, but you could store it if you want
    }

    public List<Integer> getBookings() { return bookings; }
    public void setBookings(List<Integer> bookings) { this.bookings = bookings; }
}
