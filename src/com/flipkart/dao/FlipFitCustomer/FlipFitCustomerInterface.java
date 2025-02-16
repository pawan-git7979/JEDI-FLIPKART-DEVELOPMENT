package com.flipkart.dao.FlipFitCustomer;

import com.flipkart.bean.*;

import java.util.List;

public interface FlipFitCustomerInterface {
    boolean registerGymCustomer(FlipFitGymCustomer customer);
    List<String> getAvailableCities();
    List<FlipFitGymCenter> getGymsByCity(String city);
    List<FlipFitGymSlot> getAvailableSlots(int gymId);
    void addToWaitlist(int userId, int slotId);
    FlipFitBooking bookSlot(int userId, int gymId, int slotId);
    List<FlipFitBooking> getUserBookings(int userId);
    List<FlipFitPayment> getUserPayments(int userId);
    List<FlipFitNotification> getUserNotifications(int userId);
}
