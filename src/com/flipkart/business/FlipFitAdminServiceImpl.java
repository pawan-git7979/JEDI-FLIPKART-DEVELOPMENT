package com.flipkart.business;

import com.flipkart.dao.FlipFitAdmin.FlipFitAdminInterface;
import com.flipkart.dao.FlipFitAdmin.FlipFitAdminImpl;
import com.flipkart.bean.FlipFitGymCenter;
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
    public void processPendingGymCenterRequests(Scanner scanner) {
        List<FlipFitGymCenter> pendingGymCenters = adminDAO.getPendingGymCenterRequests();

        if (pendingGymCenters.isEmpty()) {
            System.out.println("No pending gym center requests.");
            return;
        }

        // Loop through each pending gym center request
        pendingGymCenters.forEach(gymCenter -> {
            System.out.println("Gym ID: " + gymCenter.getId() + " | Gym Name: " + gymCenter.getName());
            System.out.println("Approve (A) or Reject (R)? ");
            String decision = scanner.next().trim().toUpperCase();

            try {
                // If the decision is to approve the gym center
                if (decision.equals("A")) {
                    if (!adminDAO.approveGymCenter(gymCenter.getId())) {
                        throw new AdminException("Error approving gym center with ID: " + gymCenter.getId());
                    }
                    System.out.println("Gym Center Approved.");
                }
                // If the decision is to reject the gym center
                else if (decision.equals("R")) {
                    if (!adminDAO.rejectGymCenter(gymCenter.getId())) {
                        throw new AdminException("Error rejecting gym center with ID: " + gymCenter.getId());
                    }
                    System.out.println("Gym Center Rejected.");
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
