package com.flipkart.business;

import com.flipkart.dao.FlipFitAdmin.FlipFitAdminInterface;
import com.flipkart.dao.FlipFitAdmin.FlipFitAdminImpl;
import com.flipkart.bean.FlipFitGymOwner;
import java.util.List;
import java.util.Scanner;

public class FlipFitAdminService {
    private FlipFitAdminInterface adminDAO = new FlipFitAdminImpl();

    public void processPendingGymOwnerRequests(Scanner scanner) {
        List<FlipFitGymOwner> pendingOwners = adminDAO.getPendingGymOwnerRequests();

        if (pendingOwners.isEmpty()) {
            System.out.println("No pending gym owner requests.");
            return;
        }

        for (FlipFitGymOwner owner : pendingOwners) {
            System.out.println("Owner ID: " + owner.getUserId() + " | Name: " + owner.getName());
            System.out.println("Approve (A) or Reject (R)? ");
            String decision = scanner.next().trim().toUpperCase();

            if (decision.equals("A")) {
                if (adminDAO.approveGymOwner(owner.getUserId())) {
                    System.out.println("Gym Owner Approved.");
                } else {
                    System.out.println("Error approving owner.");
                }
            } else if (decision.equals("R")) {
                if (adminDAO.rejectGymOwner(owner.getUserId())) {
                    System.out.println("Gym Owner Rejected.");
                } else {
                    System.out.println("Error rejecting owner.");
                }
            } else {
                System.out.println("Invalid input. Skipping this request.");
            }
        }
    }

    public void viewAllCustomers() {
        System.out.println("Fetching all gym customers..."); // Placeholder
    }

    public void viewAllGymOwners() {
        System.out.println("Fetching all gym owners..."); // Placeholder
    }
}
