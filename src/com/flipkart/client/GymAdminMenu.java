package com.flipkart.client;

public class GymAdminMenu {
    public static void showMenu(String username) {
        int choice;
        do {
            System.out.println("\n=== Gym Admin Menu ===");
            System.out.println("1. Approve new Gym requests");
            System.out.println("2. Generate Reports");
            System.out.println("3. Manage Users");
            System.out.println("4. Logout");
            choice = IOUtils.getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    System.out.println("Approving new gym requests...");
                    break;
                case 2:
                    System.out.println("Generating reports...");
                    break;
                case 3:
                    System.out.println("Managing users...");
                    break;
                case 4:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }
}
