package com.flipkart.client;

import java.util.Scanner;
import com.flipkart.business.FlipFitCustomerService;
import com.flipkart.utils.FlipFitIOUtils;

public class FlipFitCustomerMenu {
    private FlipFitCustomerService customerService;
    private int userId;

    public FlipFitCustomerMenu(int userId) { // ✅ Constructor initializes userId
        this.customerService = new FlipFitCustomerService();
        this.userId = userId;
    }

    public void showMenu(Scanner scanner) { // ✅ Instance method, no static
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== FlipFit Customer Menu ===");
            System.out.println("1. Book Slot");
            System.out.println("2. View Bookings");
            System.out.println("3. View Payment Info");
            System.out.println("4. View Notifications");
            System.out.println("5. Logout");

            int choice = FlipFitIOUtils.getIntInput("Enter your choice: ", scanner);

            switch (choice) {
                case 1:
                    customerService.bookSlot(scanner, userId);
                    break;
                case 2:
                    customerService.viewBookings(userId);
                    break;
                case 3:
                    customerService.viewPayments(userId);
                    break;
                case 4:
                    customerService.viewNotifications(userId);
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
