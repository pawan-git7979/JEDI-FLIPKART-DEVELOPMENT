package com.flipkart.bean;

import java.util.List;

/**
 * FlipFitAdmin class represents an admin user in the FlipFit system.
 * It extends FlipFitUser and includes additional functionality
 * related to managing gym requests.
 */
public class FlipFitAdmin extends FlipFitUser {
    private List<String> gymRequests;

    /**
     * Default Constructor.
     * Initializes a FlipFitAdmin object with default values.
     */
    public FlipFitAdmin() {
        super();
    }

    /**
     * Parameterized Constructor.
     * Initializes a FlipFitAdmin object with the given details.
     *
     * @param userId      The unique ID of the admin user.
     * @param name        The name of the admin.
     * @param email       The email address of the admin.
     * @param password    The password for authentication.
     * @param address     The address of the admin.
     * @param gymRequests List of pending gym requests assigned to the admin.
     */
    public FlipFitAdmin(int userId, String name, String email, String password, String address, List<String> gymRequests) {
        super(userId, name, email, password, FlipFitUser.ROLE_ADMIN, address);
        this.gymRequests = gymRequests;
    }

    /**
     * Gets the list of gym requests assigned to the admin.
     *
     * @return A list of gym request IDs.
     */
    public List<String> getGymRequests() {
        return gymRequests;
    }

    /**
     * Sets the list of gym requests assigned to the admin.
     *
     * @param gymRequests A list of gym request IDs to be assigned.
     */
    public void setGymRequests(List<String> gymRequests) {
        this.gymRequests = gymRequests;
    }

    /**
     * Returns a string representation of the FlipFitAdmin object.
     *
     * @return A formatted string containing admin details and gym requests.
     */
    @Override
    public String toString() {
        return "FlipFitAdmin { " + super.toString() + ", gymRequests=" + gymRequests + " }";
    }
}
