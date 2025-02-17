package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.business.FlipFitAuthServiceImpl;
import com.flipkart.business.FlipFitAuthServiceInterface;
import com.flipkart.utils.FlipFitIOUtils;
import com.flipkart.bean.FlipFitUser;

/**
 * The FlipFitApplicationMenu class represents the main menu of the FlipFit application.
 * It handles user login, registration, and navigation to different role-based menus
 * for customers, gym owners, or admins based on the credentials entered.
 */
public class FlipFitApplicationMenu {

    /**
     * The main method that runs the FlipFit application. It displays the main menu
     * to the user, allowing them to log in, register, or exit the application.
     *
     * @param args Command line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            FlipFitAuthServiceInterface authService = new FlipFitAuthServiceImpl();

            boolean exit = false;

            // Loop to keep showing the main menu until the user chooses to exit
            while (!exit) {
                System.out.println("\n=========================================");
                System.out.println("|        FlipFit Main Menu             |");
                System.out.println("=========================================");
                System.out.printf("| %-3s | %-30s |\n", "No.", "Option");
                System.out.println("-----------------------------------------");
                System.out.printf("| %-3s | %-30s |\n", "1", "Login");
                System.out.printf("| %-3s | %-30s |\n", "2", "Register (Gym Customer / Gym Owner)");
                System.out.printf("| %-3s | %-30s |\n", "3", "Exit");
                System.out.println("=========================================");

                // Getting user input for menu choice
                int choice = FlipFitIOUtils.getIntInput("Enter your choice: ", scanner);

                // Handling user choice
                switch (choice) {
                    case 1:
                        // Prompt for email and password for login
                        String email = FlipFitIOUtils.getStringInput("Enter Email: ", scanner);
                        String password = FlipFitIOUtils.getStringInput("Enter Password: ", scanner);

                        // Attempt login and navigate to appropriate menu if successful
                        FlipFitUser user = authService.login(email, password);
                        if (user != null) {
                            navigateToRoleMenu(user, scanner); // Pass entire user object to role-based menu
                        } else {
                            System.out.println("❌ Invalid credentials. Please try again.");
                        }
                        break;
                    case 2:
                        // Register a new user (gym customer or gym owner)
                        authService.registerUser(scanner);
                        break;
                    case 3:
                        // Exit the application
                        exit = true;
                        System.out.println("👋 Exiting FlipFit. Goodbye!");
                        break;
                    default:
                        // Handle invalid menu choices
                        System.out.println("❌ Invalid choice. Please try again.");
                }
            }
        }
    }

    /**
     * Navigates to the appropriate role-based menu (Customer, Owner, Admin)
     * based on the user's role.
     *
     * @param user The logged-in user object containing role information
     * @param scanner The scanner object used for user input
     */
    private static void navigateToRoleMenu(FlipFitUser user, Scanner scanner) {
        switch (user.getRole().toUpperCase()) {
            case FlipFitUser.ROLE_CUSTOMER:
                String customerName = user.getName();
                // Navigate to the customer menu
                new FlipFitCustomerMenu(user.getUserId(), customerName).showMenu(scanner);
                break;
            case FlipFitUser.ROLE_OWNER:
                String ownerName = user.getName();
                // Navigate to the gym owner menu
                new FlipFitGymOwnerMenu(user.getUserId(), ownerName).showMenu(scanner);
                break;
            case FlipFitUser.ROLE_ADMIN:
                String adminName = user.getName();
                // Navigate to the admin menu
                new FlipFitAdminMenu().showMenu(scanner, adminName);
                break;
            default:
                // Handle invalid role
                System.out.println("❌ Invalid role. Returning to main menu.");
        }
    }
}
