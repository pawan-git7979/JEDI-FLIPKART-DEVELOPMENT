package com.flipkart.business;

import com.flipkart.bean.FlipFitGymCustomer;
import com.flipkart.bean.FlipFitGymCenter;

public interface FlipFitCustomerServiceInterface {
    void viewAvailableCenters(String city);
    void viewAvailableSlots(FlipFitGymCenter center);
    void bookSlot(FlipFitGymCustomer customer, FlipFitGymCenter center, String slotId);
}
