package com.flipkart.dao.FlipFitCustomer;

import com.flipkart.bean.*;
import com.flipkart.exception.SlotNotAvailableException; // Import the custom exception for slots
import com.flipkart.exception.PaymentFailureException; // Import the custom exception for payments

import java.util.List;

public interface FlipFitCustomerInterface {

    // Register Gym Customer method
    boolean registerGymCustomer(FlipFitGymCustomer customer);

    // Get available cities method
    List<String> getAvailableCities();

    // Get gyms by city method
    List<FlipFitGymCenter> getGymsByCity(String city);

    // Get available slots method, updated to throw SlotNotAvailableException
    List<FlipFitGymSlot> getAvailableSlots(int gymId) throws SlotNotAvailableException;

    // Add to waitlist method
    void addToWaitlist(int userId, int slotId);

    // Book slot method, updated to throw SlotNotAvailableException
    FlipFitBooking bookSlot(int userId, int gymId, Integer slotId, int booked) throws SlotNotAvailableException;

    // Get user bookings method
    List<FlipFitBooking> getUserBookings(int userId);

    // Get user payments method, updated to throw PaymentFailureException
    List<FlipFitPayment> getUserPayments(int userId) throws PaymentFailureException;

    // Get user notifications method
    List<FlipFitNotification> getUserNotifications(int userId);
}
