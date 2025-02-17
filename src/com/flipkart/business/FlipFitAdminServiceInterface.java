package com.flipkart.business;

import java.util.Scanner;

/**
 * Interface for the FlipFitAdminService, defining the operations an admin can perform.
 * This includes processing pending gym owner requests and viewing all customers, gym owners, and gym centers.
 */
public interface FlipFitAdminServiceInterface {

    /**
     * Processes pending gym owner requests, allowing the admin to approve or reject them.
     * @param scanner Scanner object to capture user input for approving or rejecting gym owner requests.
     */
    void processPendingGymOwnerRequests(Scanner scanner);

    /**
     * Displays a list of all gym customers.
     */
    void viewAllCustomers();

    /**
     * Displays a list of all gym owners.
     */
    void viewAllGymOwners();

    /**
     * Displays a list of all gym centers.
     */
    void viewAllGyms();
}
