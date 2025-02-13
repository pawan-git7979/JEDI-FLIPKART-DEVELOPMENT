package com.flipkart.client;

public class FlipFitGymOwnerMenu {
    public static void showMenu(String username) {
        int choice;
        do {
            System.out.println("\n=== Gym Owner Menu ===");
            System.out.println("1. Add a Gym Center");
            System.out.println("2. Update Gym Info");
            System.out.println("3. Add/Update Slots");
            System.out.println("4. Logout");
            choice = IOUtils.getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    System.out.println("Adding a new gym center...");
                    break;
                case 2:
                    System.out.println("Updating gym info...");
                    break;
                case 3:
                    System.out.println("Adding or updating slots...");
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
