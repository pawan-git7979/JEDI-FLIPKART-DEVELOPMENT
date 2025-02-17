package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.business.FlipFitAuthServiceImpl;
import com.flipkart.business.FlipFitAuthServiceInterface;
import com.flipkart.utils.FlipFitIOUtils;
import com.flipkart.bean.FlipFitUser;

public class FlipFitApplicationMenu {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            FlipFitAuthServiceInterface authService = new FlipFitAuthServiceImpl();

            boolean exit = false;

            while (!exit) {
                System.out.println("\n=== FlipFit Main Menu ===");
                System.out.println("1. Login");
                System.out.println("2. Register (Gym Customer / Gym Owner)");
                System.out.println("3. Exit");

                int choice = FlipFitIOUtils.getIntInput("Enter your choice: ", scanner);

                switch (choice) {
                    case 1:
                        String email = FlipFitIOUtils.getStringInput("Enter Email: ", scanner);
                        String password = FlipFitIOUtils.getStringInput("Enter Password: ", scanner);

                        FlipFitUser user = authService.login(email, password);
                        if (user != null) {
                            navigateToRoleMenu(user, scanner); // Pass entire user object
                        } else {
                            System.out.println("Invalid credentials. Please try again.");
                        }
                        break;
                    case 2:
                        authService.registerUser(scanner);
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Exiting FlipFit. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private static void navigateToRoleMenu(FlipFitUser user, Scanner scanner) {
        switch (user.getRole().toUpperCase()) {
            case FlipFitUser.ROLE_CUSTOMER:
                new FlipFitCustomerMenu(user.getUserId()).showMenu(scanner); // ✅ Now works correctly
                break;
            case FlipFitUser.ROLE_OWNER:
                new FlipFitGymOwnerMenu(user.getUserId()).showMenu(scanner); // ✅ Fixed for OwnerMenu
                break;
            case FlipFitUser.ROLE_ADMIN:
                new FlipFitAdminMenu().showMenu(scanner); // ✅ No userId required for admin
                break;
            default:
                System.out.println("Invalid role. Returning to main menu.");
        }
    }
}
