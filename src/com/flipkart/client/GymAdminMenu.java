package com.flipkart.client;

import java.util.ArrayList;
import java.util.List;

public class GymAdminMenu {

    public static void showMenu(String username) {
        while (true) {
            System.out.println("\n=== Gym Admin Menu ===");
            System.out.println("1. Approve new Gym requests");
            System.out.println("2. Generate Reports");
            System.out.println("3. Manage Users");
            System.out.println("4. Logout");

            int choice = IOUtils.getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    approveGymRequests();
                    break;
                case 2:
                    generateReports();
                    break;
                case 3:
                    manageUsers();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return; // Exit the loop and the method
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void approveGymRequests() {
        while (true) {
            List<String> gymRequests = new ArrayList<>(); // Simulate empty list for now
            if (gymRequests.isEmpty()) {
                System.out.println("No pending gym requests.");
                System.out.println("1. Exit to main menu");
                int choice = IOUtils.getIntInput("Enter your choice: ");
                if(choice == 1) return;
                else continue; // to avoid invalid input and loop again
            }

            System.out.println("\n--- Gym Requests ---");
            for (int i = 0; i < gymRequests.size(); i++) {
                System.out.println((i + 1) + ". " + gymRequests.get(i));
            }

            int choice = IOUtils.getIntInput("Enter the number to approve (or 0 to return):");

            if (choice == 0) {
                return; // Return to the main menu
            } else if (choice > 0 && choice <= gymRequests.size()) {
                // Implement approval logic here (e.g., remove from the list)
                System.out.println("Approving request: " + gymRequests.get(choice - 1));
                // gymRequests.remove(choice - 1);  // Example: Remove after approval.
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }


    private static void generateReports() {
        while (true) {
            List<String> reports = new ArrayList<>(); // Simulate empty list for now
            if (reports.isEmpty()) {
                System.out.println("No reports available yet.");
                System.out.println("1. Exit to main menu");
                int choice = IOUtils.getIntInput("Enter your choice: ");
                if(choice == 1) return;
                else continue; // to avoid invalid input and loop again
            }

            System.out.println("\n--- Reports ---");
            for (int i = 0; i < reports.size(); i++) {
                System.out.println((i + 1) + ". " + reports.get(i));
            }

            int choice = IOUtils.getIntInput("Enter the number to view (or 0 to return):");

            if (choice == 0) {
                return; // Return to the main menu
            } else if (choice > 0 && choice <= reports.size()) {
                System.out.println("Viewing report: " + reports.get(choice - 1));
                // Implement report viewing/downloading logic here.
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private static void manageUsers() {
        while (true) {
            List<String> users = new ArrayList<>(); // Simulate empty list for now
            if (users.isEmpty()) {
                System.out.println("No users found.");
                System.out.println("1. Exit to main menu");
                int choice = IOUtils.getIntInput("Enter your choice: ");
                if(choice == 1) return;
                else continue; // to avoid invalid input and loop again
            }

            System.out.println("\n--- Users ---");
            for (int i = 0; i < users.size(); i++) {
                System.out.println((i + 1) + ". " + users.get(i));
            }

            int choice = IOUtils.getIntInput("Enter the number to manage (or 0 to return):");

            if (choice == 0) {
                return; // Return to the main menu
            } else if (choice > 0 && choice <= users.size()) {
                System.out.println("Managing user: " + users.get(choice - 1));
                // Implement user management logic here.
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}