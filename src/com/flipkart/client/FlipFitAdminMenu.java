package com.flipkart.client;

import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.io.FlipFitIO;
import com.flipkart.bean.FlipFitUser;
import com.flipkart.business.FlipFitAdminService;
import java.util.List;

public class FlipFitAdminMenu {

    public static void showMenu(FlipFitUser admin, List<FlipFitGymOwner> pendingOwners,
                                List<FlipFitUser> allUsers) {
        FlipFitAdminService adminService = new FlipFitAdminService();
        int choice;
        do {
            System.out.println("\n=== FlipFit Admin Menu ===");
            System.out.println("1. View Pending Gym Owner Requests");
            System.out.println("2. View All Gym Customers");
            System.out.println("3. View All Gym Owners");
            System.out.println("4. Logout");
            choice = FlipFitIO.getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    if (pendingOwners.isEmpty()) {
                        System.out.println("No pending gym owner requests.");
                    } else {
                        System.out.println("Pending Gym Owner Registrations:");
                        for (FlipFitGymOwner owner : pendingOwners) {
                            System.out.println(owner);
                        }
                        int approveChoice = FlipFitIO.getIntInput("Enter Gym Owner ID to approve (0 to cancel): ");
                        if (approveChoice != 0) {
                            boolean found = false;
                            for (FlipFitGymOwner owner : pendingOwners) {
                                if (owner.getUserId() == approveChoice) {
                                    pendingOwners.remove(owner);
                                    System.out.println("Gym Owner " + owner.getName() + " approved.");
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("Gym Owner ID not found in pending list.");
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("Registered Gym Customers:");
                    for (FlipFitUser user : allUsers) {
                        if (user.getRole().equalsIgnoreCase("GymCustomer")) {
                            System.out.println(user);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Registered Gym Owners:");
                    for (FlipFitUser user : allUsers) {
                        if (user.getRole().equalsIgnoreCase("GymOwner")) {
                            System.out.println(user);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Logging out from Admin account...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }
}
