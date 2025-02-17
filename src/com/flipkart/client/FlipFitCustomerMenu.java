package com.flipkart.client;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.flipkart.business.FlipFitCustomerServiceImpl;
import com.flipkart.business.FlipFitCustomerServiceInterface;
import com.flipkart.utils.FlipFitIOUtils;

/**
 * The FlipFitCustomerMenu class represents the menu interface for a customer in the FlipFit application.
 * It provides various options for the customer to book slots, view bookings, payment information,
 * and notifications. It also allows the customer to log out from the application.
 */
public class FlipFitCustomerMenu {
    private FlipFitCustomerServiceInterface customerService;
    private int userId;
    private String customerName;

    /**
     * Constructs a new FlipFitCustomerMenu instance with the specified user ID and customer name.
     *
     * @param userId The ID of the customer user
     * @param customerName The name of the customer
     */
    public FlipFitCustomerMenu(int userId, String customerName) {
        this.customerService = new FlipFitCustomerServiceImpl(); // Using interface for loose coupling
        this.userId = userId;
        this.customerName = customerName;
    }

    /**
     * Displays the customer menu with available options for the customer to choose from.
     * The options include booking a slot, viewing bookings, viewing payment info, and viewing notifications.
     * The menu will continuously loop until the customer chooses to log out.
     *
     * @param scanner The Scanner object used to capture user input
     */
    public void showMenu(Scanner scanner) {
        boolean exit = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy 'at' h:mm a");

        // Loop until the user decides to exit the menu
        while (!exit) {
            LocalDateTime now = LocalDateTime.now();
            String formattedNow = now.format(formatter);

            // Display the menu with current date and time
            System.out.println("\n=====================================");
            System.out.println("|         FlipFit Customer Menu      |");
            System.out.println("=====================================");
            System.out.println("| Welcome, " + customerName + "!                |");
            System.out.println("| Current Date and Time: " + formattedNow + " |");
            System.out.println("=====================================");
            System.out.printf("| %-3s | %-25s |\n", "No.", "Option");
            System.out.println("-------------------------------------");
            System.out.printf("| %-3s | %-25s |\n", "1", "Book Slot");
            System.out.printf("| %-3s | %-25s |\n", "2", "View Bookings");
            System.out.printf("| %-3s | %-25s |\n", "3", "View Payment Info");
            System.out.printf("| %-3s | %-25s |\n", "4", "View Notifications");
            System.out.printf("| %-3s | %-25s |\n", "5", "Logout");
            System.out.println("=====================================");

            // Get the user's choice
            int choice = FlipFitIOUtils.getIntInput("Enter your choice: ", scanner);

            // Handle user's choice and delegate the operation to the service layer
            switch (choice) {
                case 1:
                    customerService.bookSlot(scanner, userId); // Call service to book a slot
                    break;
                case 2:
                    customerService.viewBookings(userId); // Call service to view bookings
                    break;
                case 3:
                    customerService.viewPayments(userId); // Call service to view payment info
                    break;
                case 4:
                    customerService.viewNotifications(userId); // Call service to view notifications
                    break;
                case 5:
                    exit = true; // Exit the loop and log out
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}
