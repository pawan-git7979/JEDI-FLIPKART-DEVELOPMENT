package com.flipkart.client;

public class GymCustomerMenu {
    public static void showMenu(String username) {
        int choice;
        do {
            System.out.println("\n=== Gym Customer Menu ===");
            System.out.println("1. View Gym Availability");
            System.out.println("2. Book a Slot");
            System.out.println("3. Cancel a Booking");
            System.out.println("4. Logout");
            choice = IOUtils.getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    System.out.println("Displaying gym availability...");
                    break;
                case 2:
                    System.out.println("Booking a slot...");
                    break;
                case 3:
                    System.out.println("Cancelling a booking...");
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
