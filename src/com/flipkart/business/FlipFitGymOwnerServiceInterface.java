package com.flipkart.business;

import java.util.List;
import java.util.Scanner;

/**
 * Interface defining services for gym owners within the FlipFit system.
 * This interface includes methods for managing gym centers, slots, and bookings.
 */
public interface FlipFitGymOwnerServiceInterface {

    /**
     * Method to add a new gym center.
     *
     * @param scanner The scanner used to capture user input.
     * @param ownerId The ID of the gym owner adding the gym.
     * @return boolean indicating whether the gym was added successfully.
     */
    boolean addGymCenter(Scanner scanner, int ownerId);

    /**
     * Method to update gym center information.
     *
     * @param scanner The scanner used to capture user input.
     * @param ownerId The ID of the gym owner updating the gym.
     * @return boolean indicating whether the gym information was updated successfully.
     */
    boolean updateGymInfo(Scanner scanner, int ownerId);

    /**
     * Method to add or update a gym slot.
     *
     * @param scanner The scanner used to capture user input.
     * @param ownerId The ID of the gym owner adding or updating the slot.
     * @return boolean indicating whether the slot was added or updated successfully.
     */
    boolean addOrUpdateSlot(Scanner scanner, int ownerId);

    /**
     * Method to view all bookings for a gym owned by the gym owner.
     *
     * @param ownerId The ID of the gym owner whose bookings are being viewed.
     * @return List of bookings for the gym center.
     */
    List<String> viewBookings(int ownerId);
}
