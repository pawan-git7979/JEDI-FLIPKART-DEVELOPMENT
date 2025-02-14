package com.flipkart.business;

import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitGymSlot;
import com.flipkart.bean.FlipFitGymCenter;
import java.util.List;

public class FlipFitGymOwnerService implements FlipFitGymOwnerServiceInterface {

    private List<FlipFitGymCenter> centers;

    public FlipFitGymOwnerService(List<FlipFitGymCenter> centers) {
        this.centers = centers;
    }

    @Override
    public void addGymCenter(FlipFitGymOwner owner, FlipFitGymCenter center) {
        centers.add(center);
        System.out.println(owner.getName() + " added Gym Center: " + center.getName());
    }

    @Override
    public void updateGymInfo(FlipFitGymOwner owner, int centerId, String newName, String newLocation) {
        for (FlipFitGymCenter c : centers) {
            if (c.getId() == centerId) {
                c.setName(newName);
                c.setLocation(newLocation);
                System.out.println("Gym Center updated successfully by " + owner.getName());
                return;
            }
        }
        System.out.println("Gym Center with ID " + centerId + " not found.");
    }

    @Override
    public void addOrUpdateSlot(FlipFitGymOwner owner, int centerId, FlipFitGymSlot slot) {
        for (FlipFitGymCenter c : centers) {
            if (c.getId() == centerId) {
                // If the center has no slots yet, initialize the list.
                if (c.getSlots() == null) {
                    c.setSlots(new java.util.ArrayList<>());
                }
                boolean exists = false;
                for (FlipFitGymSlot s : c.getSlots()) {
                    if (s.getSlotId().equals(slot.getSlotId())) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    c.getSlots().add(slot);
                    System.out.println("Slot " + slot.getSlotId() + " added to " + c.getName());
                } else {
                    System.out.println("Slot " + slot.getSlotId() + " already exists. Updating slot details if needed...");
                    // You could update details here if desired.
                }
                return;
            }
        }
        System.out.println("Gym Center with ID " + centerId + " not found for slot update.");
    }
}
