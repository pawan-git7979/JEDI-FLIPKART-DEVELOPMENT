package com.flipkart.client;

import java.util.List;
import java.util.Scanner;
import com.flipkart.business.FlipFitGymOwnerService;
import com.flipkart.utils.FlipFitIOUtils;

public class FlipFitGymOwnerMenu {
    private FlipFitGymOwnerService ownerService;
    private int userId;

    public FlipFitGymOwnerMenu(int userId) {
        this.ownerService = new FlipFitGymOwnerService();
        this.userId = userId;
    }

    public void showMenu(Scanner scanner) {
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
                    boolean gymAdded = ownerService.addGymCenter(scanner, userId);
                    System.out.println(gymAdded ? "✅ Gym center added successfully! Awaiting admin approval." : "❌ Failed to add gym center.");
                    break;

                case 2:
                    boolean gymUpdated = ownerService.updateGymInfo(scanner, userId);
                    System.out.println(gymUpdated ? "✅ Gym information updated successfully!" : "❌ Failed to update gym details.");
                    break;

                case 3:
                    boolean slotUpdated = ownerService.addOrUpdateSlot(scanner, userId);
                    System.out.println(slotUpdated ? "✅ Slot added/updated successfully!" : "❌ Failed to update slot.");
                    break;

                case 4:
                    List<String> bookings = ownerService.viewBookings(userId);
                    if (bookings.isEmpty()) {
                        System.out.println("📌 No bookings found.");
                    } else {
                        bookings.forEach(System.out::println);
                    }
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
