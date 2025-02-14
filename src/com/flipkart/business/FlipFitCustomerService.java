package com.flipkart.business;

import com.flipkart.bean.FlipFitGymCustomer;
import com.flipkart.bean.FlipFitGymSlot;
import com.flipkart.bean.FlipFitGymCenter;
import java.util.List;

public class FlipFitCustomerService implements FlipFitCustomerServiceInterface {

    private List<FlipFitGymCenter> centers;

    public FlipFitCustomerService(List<FlipFitGymCenter> centers) {
        this.centers = centers;
    }

    @Override
    public void viewAvailableCenters(String city) {
        System.out.println("Available Gym Centers in " + city + ":");
        boolean foundAny = false;
        for (FlipFitGymCenter center : centers) {
            if (center.getLocation().equalsIgnoreCase(city)) {
                System.out.println("Center ID: " + center.getId() + " | Name: " + center.getName());
                foundAny = true;
            }
        }
        if (!foundAny) {
            System.out.println("No centers found in this city.");
        }
    }

    @Override
    public void viewAvailableSlots(FlipFitGymCenter center) {
        System.out.println("Available Slot for Center: " + center.getName());
        if (center.getSlots().isEmpty()) {
            System.out.println("No slots available at the moment.");
            return;
        }
        for (FlipFitGymSlot slotId : center.getSlots()) {
            System.out.println("Slot ID: " + slotId);
        }
    }

    @Override
    public void bookSlot(FlipFitGymCustomer customer, FlipFitGymCenter center, String slotId) {
        System.out.println("Booking Slot " + slotId + " at " + center.getName() + " for " + customer.getName() + "...");
        System.out.println("Slot " + slotId + " booked successfully for " + customer.getName() + "!");
    }
}
