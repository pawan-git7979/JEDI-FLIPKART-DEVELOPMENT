package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.business.FlipFitCustomerServiceImpl;
import com.flipkart.business.FlipFitCustomerServiceInterface;
import com.flipkart.utils.FlipFitIOUtils;

public class FlipFitCustomerMenu {
    private FlipFitCustomerServiceInterface customerService;
    private int userId;

        public FlipFitCustomerMenu(int userId) { // ✅ Constructor initializes userId
            this.customerService = new FlipFitCustomerServiceImpl(); // ✅ Use interface for loose coupling
            this.userId = userId;
        }

    public void showMenu(Scanner scanner) { // ✅ Instance method, no static
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=====================================");
            System.out.println("|         FlipFit Customer Menu      |");
            System.out.println("=====================================");
            System.out.printf("| %-3s | %-25s |\n", "No.", "Option");
            System.out.println("-------------------------------------");
            System.out.printf("| %-3s | %-25s |\n", "1", "Book Slot");
            System.out.printf("| %-3s | %-25s |\n", "2", "View Bookings");
            System.out.printf("| %-3s | %-25s |\n", "3", "View Payment Info");
            System.out.printf("| %-3s | %-25s |\n", "4", "View Notifications");
            System.out.printf("| %-3s | %-25s |\n", "5", "Logout");
            System.out.println("=====================================");

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
