package com.flipkart.dao.FlipFitGymOwner;

import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitGymCenter;

import java.sql.Connection;
import java.util.List;

public interface FlipFitGymOwnerInterface {

    /**
     * Registers a new gym owner in the system.
     *
     * @param owner The FlipFitGymOwner object containing owner details to be registered.
     * @return true if the owner was successfully registered, false otherwise.
     */
    boolean registerGymOwner(FlipFitGymOwner owner);

    /**
     * Retrieves a gym owner by their unique ID.
     *
     * @param ownerId The ID of the gym owner to retrieve.
     * @return The FlipFitGymOwner object representing the gym owner, or null if not found.
     */
    FlipFitGymOwner getOwnerById(int ownerId);

    /**
     * Retrieves a list of all gym owners.
     *
     * @return A list of FlipFitGymOwner objects representing all owners in the system.
     */
    List<FlipFitGymOwner> getAllOwners();

    /**
     * Updates the details of an existing gym owner.
     *
     * @param owner The FlipFitGymOwner object containing the updated owner details.
     * @return true if the owner's details were successfully updated, false otherwise.
     */
    boolean updateOwnerDetails(FlipFitGymOwner owner);

    /**
     * Adds a new gym center for the gym owner.
     *
     * @param gym The FlipFitGymCenter object containing gym details to be added.
     * @return true if the gym center was successfully added, false otherwise.
     */
    boolean addGymCenter(FlipFitGymCenter gym);

    /**
     * Updates the information of an existing gym center.
     *
     * @param gym The FlipFitGymCenter object containing the updated gym details.
     * @return true if the gym information was successfully updated, false otherwise.
     */
    boolean updateGymInfo(FlipFitGymCenter gym);

    /**
     * Adds or updates a slot for a given gym.
     *
     * @param gymId The ID of the gym where the slot will be added or updated.
     * @param startTime The start time for the slot.
     * @param endTime The end time for the slot.
     * @param seats The number of available seats for the slot.
     * @param price The price for the slot.
     * @return true if the slot was successfully added or updated, false otherwise.
     */
    boolean addOrUpdateSlot(int gymId, String startTime, String endTime, int seats, int price);

    /**
     * Retrieves a list of all bookings for a gym owner.
     *
     * @param ownerId The ID of the gym owner whose bookings are to be retrieved.
     * @return A list of strings representing the booking details for the gym owner.
     */
    List<String> viewBookings(int ownerId);
    List<String>getAllGymsByOwner(int ownerId);
}
