package com.flipkart.client;

import java.util.List;
import java.util.Scanner;

import com.flipkart.business.FlipFitGymOwnerServiceImpl;
import com.flipkart.business.FlipFitGymOwnerServiceInterface;
import com.flipkart.utils.FlipFitIOUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FlipFitGymOwnerMenu {
    private FlipFitGymOwnerServiceInterface ownerService;
    private int userId;
    private String ownerName;

    public FlipFitGymOwnerMenu(int userId, String ownerName) {
        this.ownerService = new FlipFitGymOwnerServiceImpl();
        this.userId = userId;
        this.ownerName = ownerName;
    }

    public void showMenu(Scanner scanner) {
        boolean exit = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy 'at' h:mm a");

        while (!exit) {
            LocalDateTime now = LocalDateTime.now();
            String formattedNow = now.format(formatter);

            System.out.println("\n==========================================");
            System.out.println("|         FlipFit Gym Owner Menu         |");
            System.out.println("==========================================");
            System.out.println("| Welcome, " + ownerName + "!                     |");
            System.out.println("| Current Date and Time: " + formattedNow + " |");
            System.out.println("==========================================");
            System.out.printf("| %-3s | %-30s |\n", "No.", "Option");
            System.out.println("------------------------------------------");
            System.out.printf("| %-3s | %-30s |\n", "1", "Add a Gym Center");
            System.out.printf("| %-3s | %-30s |\n", "2", "Update Gym Information");
            System.out.printf("| %-3s | %-30s |\n", "3", "Add or Update a Slot");
            System.out.printf("| %-3s | %-30s |\n", "4", "View Bookings");
            System.out.printf("| %-3s | %-30s |\n", "5", "Logout");
            System.out.println("==========================================");

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
                        System.out.println("\n===================================");
                        System.out.println("|        Your Gym Bookings        |");
                        System.out.println("===================================");
                        for (String booking : bookings) {
                            System.out.printf("| %-30s |\n", booking);
                        }
                        System.out.println("===================================");
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