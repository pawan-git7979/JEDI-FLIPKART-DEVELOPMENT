package com.flipkart.client;

import java.util.Scanner;
import com.flipkart.business.FlipFitOwnerService;
import com.flipkart.utils.FlipFitIOUtils;

public class FlipFitOwnerMenu {
    private FlipFitOwnerService ownerService;
    private int userId;

    public FlipFitOwnerMenu(int userId) { // ✅ Constructor initializes userId
        this.ownerService = new FlipFitOwnerService();
        this.userId = userId;
    }

    public void showMenu(Scanner scanner) { // ✅ Instance method, no static
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== FlipFit Gym Owner Menu ===");
            System.out.println("1. Add a Gym Center");
            System.out.println("2. Update Gym Information");
            System.out.println("3. Add or Update a Slot");
            System.out.println("4. View Bookings");
            System.out.println("5. Logout");

            int choice = FlipFitIOUtils.getIntInput("Enter your choice: ", scanner);

            switch (choice) {
                case 1:
                    ownerService.addGymCenter(scanner, userId); // ✅ Pass userId
                    break;
                case 2:
                    ownerService.updateGymInfo(scanner, userId); // ✅ Pass userId
                    break;
                case 3:
                    ownerService.addOrUpdateSlot(scanner, userId); // ✅ Pass userId
                    break;
                case 4:
                    ownerService.viewBookings(userId); // ✅ Pass userId
                    break;
                case 5:
                    exit = true;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
