package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.business.FlipFitAdminServiceInterface;
import com.flipkart.utils.FlipFitIOUtils;
import com.flipkart.business.FlipFitAdminServiceImpl;

public class FlipFitAdminMenu {
    public void showMenu(Scanner scanner) {
        FlipFitAdminServiceInterface adminService = new FlipFitAdminServiceImpl();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=========================================");
            System.out.println("|        FlipFit Admin Menu            |");
            System.out.println("=========================================");
            System.out.printf("| %-3s | %-30s |\n", "No.", "Option");
            System.out.println("-----------------------------------------");
            System.out.printf("| %-3s | %-30s |\n", "1", "View & Process Pending Gym Requests");
            System.out.printf("| %-3s | %-30s |\n", "2", "View All Gym Customers");
            System.out.printf("| %-3s | %-30s |\n", "3", "View All Gym Owners");
            System.out.printf("| %-3s | %-30s |\n", "4", "View All Gym Centers");
            System.out.printf("| %-3s | %-30s |\n", "5", "Logout");
            System.out.println("=========================================");

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
                    adminService.viewAllGyms();
                    break;
                case 5:
                    exit = true;
                    System.out.println("👋 Logging out...");
                    break;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}
