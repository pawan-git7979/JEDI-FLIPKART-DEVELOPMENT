package com.flipkart.business;

import java.util.List;
import java.util.Scanner;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.dao.FlipFitGymOwner.FlipFitGymOwnerInterface;
import com.flipkart.dao.FlipFitGymOwner.FlipFitGymOwnerImpl;
import com.flipkart.utils.FlipFitIOUtils;
import com.flipkart.exception.GymOwnerException;

public class FlipFitGymOwnerServiceImpl implements FlipFitGymOwnerServiceInterface {
    private FlipFitGymOwnerInterface ownerDAO = new FlipFitGymOwnerImpl();

    @Override
    public boolean addGymCenter(Scanner scanner, int ownerId) {
        try {
            System.out.println("\n=== Add a Gym Center ===");
            String gymName = FlipFitIOUtils.getStringInput("Enter Gym Name: ", scanner);
            String location = FlipFitIOUtils.getStringInput("Enter Location: ", scanner);

            FlipFitGymCenter gym = new FlipFitGymCenter();
            gym.setName(gymName);
            gym.setLocation(location);
            gym.setOwnerId(ownerId);
            gym.setAdminId(1); // Default Admin ID for approval process

            boolean isAdded = ownerDAO.addGymCenter(gym);
            if (!isAdded) {
                throw new GymOwnerException("Failed to add the gym center.");
            }
            return true;
        } catch (GymOwnerException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateGymInfo(Scanner scanner, int ownerId) {
        try {
            System.out.println("\n=== Update Gym Information ===");
            int gymId = FlipFitIOUtils.getIntInput("Enter Gym ID: ", scanner);
            String newName = FlipFitIOUtils.getStringInput("Enter New Gym Name: ", scanner);
            String newLocation = FlipFitIOUtils.getStringInput("Enter New Location: ", scanner);

            FlipFitGymCenter gym = new FlipFitGymCenter();
            gym.setId(gymId);
            gym.setName(newName);
            gym.setLocation(newLocation);
            gym.setOwnerId(ownerId);

            boolean isUpdated = ownerDAO.updateGymInfo(gym);
            if (!isUpdated) {
                throw new GymOwnerException("Failed to update gym information.");
            }
            return true;
        } catch (GymOwnerException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean addOrUpdateSlot(Scanner scanner, int ownerId) {
        try {
            System.out.println("\n=== Add or Update a Slot ===");
            int gymId = FlipFitIOUtils.getIntInput("Enter Gym ID: ", scanner);
            String startTime = FlipFitIOUtils.getStringInput("Enter Slot Start Time (HH:MM): ", scanner);
            String endTime = FlipFitIOUtils.getStringInput("Enter Slot End Time (HH:MM): ", scanner);
            int seats = FlipFitIOUtils.getIntInput("Enter Number of Seats: ", scanner);
            int price = FlipFitIOUtils.getIntInput("Enter Price: ", scanner);

            boolean isSlotUpdated = ownerDAO.addOrUpdateSlot(gymId, startTime, endTime, seats, price);
            if (!isSlotUpdated) {
                throw new GymOwnerException("Failed to add or update the slot.");
            }
            return true;
        } catch (GymOwnerException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<String> viewBookings(int ownerId) {
        try {
            List<String> bookings = ownerDAO.viewBookings(ownerId);
            if (bookings.isEmpty()) {
                throw new GymOwnerException("No bookings found for this gym.");
            }
            return bookings;
        } catch (GymOwnerException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
