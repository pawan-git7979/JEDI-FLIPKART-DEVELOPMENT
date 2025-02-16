package com.flipkart.client;

import java.util.Scanner;
import com.flipkart.utils.FlipFitIOUtils;
import com.flipkart.business.FlipFitAdminService;

public class FlipFitAdminMenu {
    public void showMenu(Scanner scanner) {
        FlipFitAdminService adminService = new FlipFitAdminService();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== FlipFit Admin Menu ===");
            System.out.println("1. View & Process Pending Gym  Requests");
            System.out.println("2. View All Gym Customers");
            System.out.println("3. View All Gym Owners");
            System.out.println("4. Logout");

            int choice = FlipFitIOUtils.getIntInput("Enter your choice: ", scanner);

            switch (choice) {
                case 1:
                    adminService.processPendingGymOwnerRequests(scanner); // ✅ Business logic in service layer
                    break;
                case 2:
                    adminService.viewAllCustomers();
                    break;
                case 3:
                    adminService.viewAllGymOwners();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
