package com.flipkart.client;

import java.util.Scanner;

public class FlipFitGymMain {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String LOG_FILE = "flipfit_actions.log";

    public static void main(String[] args) {
        System.out.println("Welcome to FlipFit application!");
        int choice;
        do {
            printMainMenu();
            choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    registerGymCustomer();
                    break;
                case 3:
                    registerGymOwner();
                    break;
                case 4:
                    changePassword();
                    break;
                case 5:
                    System.out.println("Exiting...Thank you for using FlipFit!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n==== Main Menu ====");
        System.out.println("1. Login");
        System.out.println("2. Registration of a Gym Customer");
        System.out.println("3. Registration of a Gym Owner");
        System.out.println("4. Change Password");
        System.out.println("5. Exit");
    }

    private static int getIntInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Please enter a valid integer: ");
        }
        return scanner.nextInt();
    }

    private static String getStringInput(String message) {
        System.out.print(message);
//        scanner.nextLine();
        return scanner.nextLine();
    }

    private static void login() {
        System.out.println("\n=== Login ===");
        String username = getStringInput("Enter username: ");
        String password = getStringInput("Enter password: ");
        String role = getStringInput("Enter role (gym customer / gym owner / gym admin): ");
        System.out.println("Logged in successfully as: " + username + " with role: " + role);
        logToFile("User logged in -> username: " + username + ", role: " + role);
        switch (role.toLowerCase()) {
            case "gym customer":
                gymCustomerMenu(username);
                break;
            case "gym owner":
                gymOwnerMenu(username);
                break;
            case "gym admin":
                gymAdminMenu(username);
                break;
            default:
                System.out.println("Unrecognized role, returning to main menu.");
                break;
        }
    }

    private static void registerGymCustomer() {
        System.out.println("\n=== Register Gym Customer ===");
        String name = getStringInput("Enter your name: ");
        String email = getStringInput("Enter your email: ");
        String pass = getStringInput("Enter your password: ");
        System.out.println("Gym customer registered successfully!");
        logToFile("New Gym Customer Registered -> Name: " + name + ", Email: " + email);
    }

    private static void registerGymOwner() {
        System.out.println("\n=== Register Gym Owner ===");
        String name = getStringInput("Enter your name: ");
        String email = getStringInput("Enter your email: ");
        String pass = getStringInput("Enter your password: ");
        System.out.println("Gym owner registered successfully!");
        logToFile("New Gym Owner Registered -> Name: " + name + ", Email: " + email);
    }

    private static void changePassword() {
        System.out.println("\n=== Change Password ===");
        String userId = getStringInput("Enter your user ID: ");
        String oldPass = getStringInput("Enter old password: ");
        String newPass = getStringInput("Enter new password: ");
        System.out.println("Password updated successfully for user ID: " + userId);
        logToFile("Password changed for User ID: " + userId);
    }

    private static void gymCustomerMenu(String username) {
        int choice;
        do {
            System.out.println("\n=== Gym Customer Menu ===");
            System.out.println("1. View Gym Availability");
            System.out.println("2. Book a Slot");
            System.out.println("3. Cancel a Booking");
            System.out.println("4. Logout");
            choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    System.out.println("Displaying gym availability...");
                    break;
                case 2:
                    System.out.println("Booking a slot...");
                    break;
                case 3:
                    System.out.println("Cancelling a booking...");
                    break;
                case 4:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice for Gym Customer Menu.");
            }
        } while (choice != 4);
        logToFile("Gym Customer [" + username + "] logged out.");
    }

    private static void gymOwnerMenu(String username) {
        int choice;
        do {
            System.out.println("\n=== Gym Owner Menu ===");
            System.out.println("1. Add a Gym Center");
            System.out.println("2. Update Gym Info");
            System.out.println("3. Add/Update Slots");
            System.out.println("4. Logout");
            choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    System.out.println("Adding a new gym center...");
                    break;
                case 2:
                    System.out.println("Updating gym info...");
                    break;
                case 3:
                    System.out.println("Adding or updating slots...");
                    break;
                case 4:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice for Gym Owner Menu.");
            }
        } while (choice != 4);
        logToFile("Gym Owner [" + username + "] logged out.");
    }

    private static void gymAdminMenu(String username) {
        int choice;
        do {
            System.out.println("\n=== Gym Admin Menu ===");
            System.out.println("1. Approve new Gym requests");
            System.out.println("2. Generate Reports");
            System.out.println("3. Manage Users");
            System.out.println("4. Logout");
            choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    System.out.println("Approving new gym requests...");
                    break;
                case 2:
                    System.out.println("Generating reports...");
                    break;
                case 3:
                    System.out.println("Managing users...");
                    break;
                case 4:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice for Gym Admin Menu.");
            }
        } while (choice != 4);
        logToFile("Gym Admin [" + username + "] logged out.");
    }

    private static void logToFile(String message) {
        try (java.io.FileWriter fw = new java.io.FileWriter(LOG_FILE, true);
             java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);
             java.io.PrintWriter out = new java.io.PrintWriter(bw)) {
            out.println(message);
        } catch (Exception e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }
}