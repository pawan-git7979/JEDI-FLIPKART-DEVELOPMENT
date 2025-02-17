package com.flipkart.business;

import com.flipkart.dao.FlipFitAdmin.FlipFitAdminInterface;
import com.flipkart.dao.FlipFitAdmin.FlipFitAdminImpl;
import com.flipkart.bean.FlipFitGymOwner;
import java.util.List;
import java.util.Scanner;
import com.flipkart.exception.AdminException;

/**
 * Implementation of the FlipFitAdminServiceInterface.
 * This class handles the admin's tasks such as processing gym owner requests,
 * viewing customers, owners, and gyms.
 */
public class FlipFitAdminServiceImpl implements FlipFitAdminServiceInterface {

    // Admin DAO implementation for accessing data
    private FlipFitAdminInterface adminDAO = new FlipFitAdminImpl();

    /**
     * Processes pending gym owner requests and asks the admin to approve or reject each request.
     * @param scanner A Scanner object to read the admin's decision.
     */
    @Override
    public void processPendingGymOwnerRequests(Scanner scanner) {
        List<FlipFitGymOwner> pendingOwners = adminDAO.getPendingGymOwnerRequests();

        if (pendingOwners.isEmpty()) {
            System.out.println("No pending gym owner requests.");
            return;
        }

        // Loop through each pending owner request
        pendingOwners.forEach(owner -> {
            System.out.println("Owner ID: " + owner.getUserId() + " | Name: " + owner.getName());
            System.out.println("Approve (A) or Reject (R)? ");
            String decision = scanner.next().trim().toUpperCase();

            try {
                // If the decision is to approve the owner
                if (decision.equals("A")) {
                    if (!adminDAO.approveGymOwner(owner.getUserId())) {
                        throw new AdminException("Error approving owner with ID: " + owner.getUserId());
                    }
                    System.out.println("Gym Owner Approved.");
                }
                // If the decision is to reject the owner
                else if (decision.equals("R")) {
                    if (!adminDAO.rejectGymOwner(owner.getUserId())) {
                        throw new AdminException("Error rejecting owner with ID: " + owner.getUserId());
                    }
                    System.out.println("Gym Owner Rejected.");
                }
                // If the input is invalid
                else {
                    System.out.println("Invalid input. Skipping this request.");
                }
            } catch (AdminException e) {
                // Handle exception, e.g., log it, show a more user-friendly message, etc.
                System.out.println(e.getMessage());
            }
        });
    }

    /**
     * Displays all customers in the system.
     */
    @Override
    public void viewAllCustomers() {
        List<String> customers = adminDAO.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No gym customers found.");
            return;
        }

        // Print each customer
        customers.forEach(System.out::println);
    }

    /**
     * Displays all gym owners in the system.
     */
    @Override
    public void viewAllGymOwners() {
        List<String> owners = adminDAO.getAllOwners();
        if (owners.isEmpty()) {
            System.out.println("No gym owners found.");
            return;
        }

        // Print each gym owner
        owners.forEach(System.out::println);
    }

    /**
     * Displays all gyms in the system.
     */
    @Override
    public void viewAllGyms() {
        List<String> gyms = adminDAO.getAllGyms();
        if (gyms.isEmpty()) {
            System.out.println("No gym centers found.");
            return;
        }

        // Print each gym center
        gyms.forEach(System.out::println);
    }
}
