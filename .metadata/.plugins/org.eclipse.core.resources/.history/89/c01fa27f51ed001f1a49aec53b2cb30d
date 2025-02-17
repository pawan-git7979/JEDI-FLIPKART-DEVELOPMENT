package com.flipkart.client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.flipkart.business.FlipFitAdminServiceInterface;
import com.flipkart.utils.FlipFitIOUtils;
import com.flipkart.business.FlipFitAdminServiceImpl;

/**
 * The FlipFitAdminMenu class provides the menu interface for the FlipFit admin.
 * It displays various options and allows the admin to interact with the system,
 * including viewing and processing pending gym requests, viewing gym customers, owners,
 * and centers, and logging out of the system.
 */
public class FlipFitAdminMenu {

    /**
     * Displays the admin menu and processes the admin's choice.
     * This method presents various options to the admin such as:
     * <ul>
     *   <li>View and process pending gym requests</li>
     *   <li>View all gym customers</li>
     *   <li>View all gym owners</li>
     *   <li>View all gym centers</li>
     *   <li>Logout</li>
     * </ul>
     * The program executes the corresponding action based on the admin's choice.
     *
     * @param scanner The scanner object used for user input
     * @param AdminName The name of the admin, which is displayed in the menu
     */
    public void showMenu(Scanner scanner, String AdminName) {
        FlipFitAdminServiceInterface adminService = new FlipFitAdminServiceImpl();
        boolean exit = false;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        // Loop to continuously show the admin menu until logout
        while (!exit) {
            LocalDateTime now = LocalDateTime.now();

            // Displaying the menu header with admin name and current date/time
            System.out.println("\n  ==============================================");
            System.out.println("  |        Welcome to FlipFit Admin Menu       |");
            System.out.println("  ==============================================");
            System.out.println("  | Welcome, " + AdminName + "!                          |");
            System.out.println("  | Current Date and Time: " + now.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy 'at' h:mm a")) + " |");
            System.out.println("  ==============================================");

            // Displaying the menu options
            System.out.printf("  | %-3s | %-40s |\n", "No.", "Option");
            System.out.println("  ----------------------------------------------");
            System.out.printf("  | %-3s | %-40s |\n", "1", "View & Process Pending Gym Requests");
            System.out.printf("  | %-3s | %-40s |\n", "2", "View All Gym Customers");
            System.out.printf("  | %-3s | %-40s |\n", "3", "View All Gym Owners");
            System.out.printf("  | %-3s | %-40s |\n", "4", "View All Gym Centers");
            System.out.printf("  | %-3s | %-40s |\n", "5", "Logout");
            System.out.println("  ==============================================");

            // Reading admin's choice
            int choice = FlipFitIOUtils.getIntInput("Enter your choice: ", scanner);

            // Performing actions based on the admin's choice
            switch (choice) {
                case 1:
                    adminService.processPendingGymOwnerRequests(scanner); // Business logic in service layer
                    break;
                case 2:
                    adminService.viewAllCustomers();
                    break;
                case 3:
                    adminService.viewAllGymOwners();
                    break;
                case 4:
                    adminService.viewAllGyms();
                    break;
                case 5:
                    exit = true;  // Exit the loop and log out
                    System.out.println("👋 Logging out...");
                    break;
                default:
                    // Handle invalid choices
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}
