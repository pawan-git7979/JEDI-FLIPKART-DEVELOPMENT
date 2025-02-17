package com.flipkart.business;

import java.util.List;
import java.util.Scanner;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.dao.FlipFitGymOwner.FlipFitGymOwnerInterface;
import com.flipkart.dao.FlipFitGymOwner.FlipFitGymOwnerImpl;
import com.flipkart.utils.FlipFitIOUtils;
import com.flipkart.exception.GymOwnerException;

/**
 * Implementation of services for gym owners within the FlipFit system.
 * This class includes methods for managing gym centers, slots, and bookings.
 */
public class FlipFitGymOwnerServiceImpl implements FlipFitGymOwnerServiceInterface {
    private FlipFitGymOwnerInterface ownerDAO = new FlipFitGymOwnerImpl();

    /**
     * Method to add a new gym center.
     * Allows gym owners to add a gym to the system.
     *
     * @param scanner The scanner used to capture user input.
     * @param ownerId The ID of the gym owner adding the gym.
     * @return boolean indicating whether the gym was added successfully.
     */
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

    /**
     * Method to update gym center information.
     * Allows gym owners to update their gym's name or location.
     *
     * @param scanner The scanner used to capture user input.
     * @param ownerId The ID of the gym owner updating the gym.
     * @return boolean indicating whether the gym information was updated successfully.
     */
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

    /**
     * Method to add or update a gym slot.
     * Allows gym owners to add or update slots for their gym.
     *
     * @param scanner The scanner used to capture user input.
     * @param ownerId The ID of the gym owner adding or updating the slot.
     * @return boolean indicating whether the slot was added or updated successfully.
     */
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

    /**
     * Method to view all bookings for a gym owned by the gym owner.
     * Allows gym owners to view the bookings made for their gym center.
     *
     * @param ownerId The ID of the gym owner whose bookings are being viewed.
     * @return List of bookings for the gym center.
     */
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
