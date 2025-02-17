package com.flipkart.client;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.flipkart.business.FlipFitGymOwnerServiceImpl;
import com.flipkart.business.FlipFitGymOwnerServiceInterface;
import com.flipkart.utils.FlipFitIOUtils;

/**
 * The FlipFitGymOwnerMenu class represents the menu interface for a gym owner in the FlipFit application.
 * It provides various options for the gym owner to manage gym details, slots, bookings, and log out.
 */
public class FlipFitGymOwnerMenu {
    private FlipFitGymOwnerServiceInterface ownerService;
    private int userId;
    private String ownerName;

    /**
     * Constructs a new FlipFitGymOwnerMenu instance with the specified user ID and owner name.
     *
     * @param userId The ID of the gym owner
     * @param ownerName The name of the gym owner
     */
    public FlipFitGymOwnerMenu(int userId, String ownerName) {
        this.ownerService = new FlipFitGymOwnerServiceImpl(); // Using interface for loose coupling
        this.userId = userId;
        this.ownerName = ownerName;
    }

    /**
     * Displays the gym owner menu with available options for the gym owner to choose from.
     * The options include adding or updating gym details, adding or updating slots, viewing bookings, and logging out.
     * The menu will continuously loop until the gym owner chooses to log out.
     *
     * @param scanner The Scanner object used to capture user input
     */
    public void showMenu(Scanner scanner) {
        boolean exit = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy 'at' h:mm a");

        // Loop until the gym owner decides to exit the menu
        while (!exit) {
            LocalDateTime now = LocalDateTime.now();
            String formattedNow = now.format(formatter);

            // Display the menu with current date and time
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

            // Get the gym owner's choice
            int choice = FlipFitIOUtils.getIntInput("Enter your choice: ", scanner);

            // Handle the user's choice and delegate the operation to the service layer
            switch (choice) {
                case 1:
                    boolean gymAdded = ownerService.addGymCenter(scanner, userId); // Add gym center
                    System.out.println(gymAdded ? "✅ Gym center added successfully! Awaiting admin approval." : "❌ Failed to add gym center.");
                    break;

                case 2:
                    boolean gymUpdated = ownerService.updateGymInfo(scanner, userId); // Update gym info
                    System.out.println(gymUpdated ? "✅ Gym information updated successfully!" : "❌ Failed to update gym details.");
                    break;

                case 3:
                    boolean slotUpdated = ownerService.addOrUpdateSlot(scanner, userId); // Add or update a slot
                    System.out.println(slotUpdated ? "✅ Slot added/updated successfully!" : "❌ Failed to update slot.");
                    break;

                case 4:
                    List<String> bookings = ownerService.viewBookings(userId); // View gym bookings
                    if (bookings.isEmpty()) {
                        System.out.println("📌 No bookings found.");
                    } else {
                        System.out.println("\n===================================");
                        System.out.println("|        Your Gym Bookings        |");
                        System.out.println("===================================");
                        for (String booking : bookings) {
                            System.out.printf("| %-30s |\n", booking); // Display each booking
                        }
                        System.out.println("===================================");
                    }
                    break;

                case 5:
                    exit = true; // Exit the menu
                    System.out.println("👋 Logging out...");
                    break;

                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}
