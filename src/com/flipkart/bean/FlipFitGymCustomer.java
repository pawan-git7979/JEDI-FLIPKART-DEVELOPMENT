package com.flipkart.bean;
import java.util.List;


public class FlipFitGymCustomer extends FlipFitUser {
    private List<Integer> bookings; 
    public FlipFitGymCustomer() {}

    public FlipFitGymCustomer(int userId, String name, String email, String password, String membershipType) {
        super(userId, name, email, password, "Customer");
    }
    public List<Integer> getBookings() { return bookings; }
    public void setBookings(List<Integer> bookings) { this.bookings = bookings; }

    
}
