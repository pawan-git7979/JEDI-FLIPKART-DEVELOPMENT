package com.flipkart.business;

import com.flipkart.dao.FlipFitAdmin.FlipFitAdminInterface;
import com.flipkart.dao.FlipFitAdmin.FlipFitAdminImpl;
import com.flipkart.bean.FlipFitGymOwner;
import java.util.List;
import java.util.Scanner;
import com.flipkart.exception.AdminException;

public class FlipFitAdminServiceImpl implements FlipFitAdminServiceInterface {
    private FlipFitAdminInterface adminDAO = new FlipFitAdminImpl();

    @Override
    public void processPendingGymOwnerRequests(Scanner scanner) {
        List<FlipFitGymOwner> pendingOwners = adminDAO.getPendingGymOwnerRequests();

        if (pendingOwners.isEmpty()) {
            System.out.println("No pending gym owner requests.");
            return;
        }

        pendingOwners.forEach(owner -> {
            System.out.println("Owner ID: " + owner.getUserId() + " | Name: " + owner.getName());
            System.out.println("Approve (A) or Reject (R)? ");
            String decision = scanner.next().trim().toUpperCase();

            try {
                if (decision.equals("A")) {
                    if (!adminDAO.approveGymOwner(owner.getUserId())) {
                        throw new AdminException("Error approving owner with ID: " + owner.getUserId());
                    }
                    System.out.println("Gym Owner Approved.");
                } else if (decision.equals("R")) {
                    if (!adminDAO.rejectGymOwner(owner.getUserId())) {
                        throw new AdminException("Error rejecting owner with ID: " + owner.getUserId());
                    }
                    System.out.println("Gym Owner Rejected.");
                } else {
                    System.out.println("Invalid input. Skipping this request.");
                }
            } catch (AdminException e) {
                // Handle exception, e.g., log it, show a more user-friendly message, etc.
                System.out.println(e.getMessage());
            }
        });

    }

    @Override
    public void viewAllCustomers() {
        List<String> customers = adminDAO.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No gym customers found.");
            return;
        }

        customers.forEach(System.out::println);

    }

    @Override
    public void viewAllGymOwners() {
        List<String> owners = adminDAO.getAllOwners();
        if (owners.isEmpty()) {
            System.out.println("No gym owners found.");
            return;
        }

        owners.forEach(System.out::println);
    }

    @Override
    public void viewAllGyms() {
        List<String> gyms = adminDAO.getAllGyms();
        if (gyms.isEmpty()) {
            System.out.println("No gym centers found.");
            return;
        }

        gyms.forEach(System.out::println);
    }
}
