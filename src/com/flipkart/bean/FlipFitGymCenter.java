package com.flipkart.bean;

import java.util.List;

/**
 * The FlipFitGymCenter class represents a gym center in the FlipFit system.
 * It contains details such as the gym's name, location, admin, owner, and available slots.
 */
public class FlipFitGymCenter {
    private int id;
    private String name;
    private String location;
    private int adminId;
    private int ownerId;      // Gym Owner ID
    private String ownerName; // Gym Owner Name
    private List<FlipFitGymSlot> slots;

    /**
     * Default Constructor.
     * Initializes a FlipFitGymCenter object with default values.
     */
    public FlipFitGymCenter() {}

    /**
     * Parameterized Constructor.
     * Initializes a FlipFitGymCenter object with the given details.
     *
     * @param id        The unique ID of the gym center.
     * @param name      The name of the gym center.
     * @param location  The location of the gym center.
     * @param adminId   The ID of the admin managing the gym.
     * @param ownerId   The ID of the gym owner.
     * @param slots     The list of available slots in the gym.
     */
    public FlipFitGymCenter(int id, String name, String location, int adminId, int ownerId, List<FlipFitGymSlot> slots) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.adminId = adminId;
        this.ownerId = ownerId;
        this.slots = slots;
    }

    /**
     * Gets the gym center ID.
     *
     * @return The unique ID of the gym center.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the gym center ID.
     *
     * @param id The unique ID to assign to the gym center.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the gym center.
     *
     * @return The name of the gym center.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the gym center.
     *
     * @param name The name to assign to the gym center.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the location of the gym center.
     *
     * @return The location of the gym center.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the gym center.
     *
     * @param location The location to assign to the gym center.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the admin ID managing the gym.
     *
     * @return The ID of the gym admin.
     */
    public int getAdminId() {
        return adminId;
    }

    /**
     * Sets the admin ID for the gym center.
     *
     * @param adminId The ID of the admin managing the gym.
     */
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    /**
     * Gets the gym owner ID.
     *
     * @return The ID of the gym owner.
     */
    public int getOwnerId() {
        return ownerId;
    }

    /**
     * Sets the gym owner ID.
     *
     * @param ownerId The ID of the gym owner.
     */
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * Gets the gym owner's name.
     *
     * @return The name of the gym owner.
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Sets the gym owner's name.
     *
     * @param ownerName The name of the gym owner.
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * Gets the list of available slots in the gym center.
     *
     * @return A list of FlipFitGymSlot objects representing available slots.
     */
    public List<FlipFitGymSlot> getSlots() {
        return slots;
    }

    /**
     * Sets the list of available slots in the gym center.
     *
     * @param slots A list of FlipFitGymSlot objects representing available slots.
     */
    public void setSlots(List<FlipFitGymSlot> slots) {
        this.slots = slots;
    }

    /**
     * Returns a string representation of the FlipFitGymCenter object.
     *
     * @return A formatted string containing gym center details.
     */
    @Override
    public String toString() {
        return "FlipFitGymCenter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", adminId=" + adminId +
                ", ownerId=" + ownerId +
                ", ownerName='" + ownerName + '\'' +
                ", slots=" + slots +
                '}';
    }
}
