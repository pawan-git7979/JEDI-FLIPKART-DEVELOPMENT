package com.flipkart.dao.FlipFitCustomer;

import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitGymSlot;
import com.flipkart.bean.FlipFitBooking;
import java.util.List;

public interface FlipFitCustomerInterface {
    List<String> getAvailableCities();
    List<FlipFitGymCenter> getGymsByCity(String city);
    List<FlipFitGymSlot> getAvailableSlots(int gymId);
    void addToWaitlist(int userId, int slotId);
    FlipFitBooking bookSlot(int userId, int gymId, int slotId);
    List<FlipFitBooking> getUserBookings(int userId);
}
