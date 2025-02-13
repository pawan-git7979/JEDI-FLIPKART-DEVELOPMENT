package com.flipkart.client;

import java.util.Scanner;

public class FlipFitMainMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to FlipFit application!");
        int choice;
        do {
            printMainMenu();
            choice = IOUtils.getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    LoginIO.login();
                    break;
                case 2:
                    registerGymCustomer();
                    break;
                case 3:
                    registerGymOwner();
                    break;
                case 4:
                    registerFlipFitAdmin();
                    break;
                case 5:
                    changePassword();
                    break;
                case 6:
                    System.out.println("Exiting...Thank you for using FlipFit!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private static void printMainMenu() {
        System.out.println("\n==== Main Menu ====");
        System.out.println("1. Login");
        System.out.println("2. Registration of a Gym Customer");
        System.out.println("3. Registration of a Gym Owner");
        System.out.println("4. Registration of FlipFit Admin");  // Added this option
        System.out.println("5. Change Password");
        System.out.println("6. Exit");
    }

    private static void registerGymCustomer() {
        System.out.println("\n=== Register Gym Customer ===");
        String name = IOUtils.getStringInput("Enter your name: ");
        String email = IOUtils.getStringInput("Enter your email: ");
        String pass = IOUtils.getStringInput("Enter your password: ");
        System.out.println("Gym customer registered successfully!");
    }

    private static void registerGymOwner() {
        System.out.println("\n=== Register Gym Owner ===");
        String name = IOUtils.getStringInput("Enter your name: ");
        String email = IOUtils.getStringInput("Enter your email: ");
        String pass = IOUtils.getStringInput("Enter your password: ");
        System.out.println("Gym owner registered successfully!");
    }

    private static void registerFlipFitAdmin() {
        System.out.println("\n=== Register FlipFit Admin ===");
        String name = IOUtils.getStringInput("Enter admin name: ");
        String email = IOUtils.getStringInput("Enter admin email: ");
        String pass = IOUtils.getStringInput("Enter admin password: ");
        System.out.println("FlipFit admin registered successfully!");
    }

    private static void changePassword() {
        System.out.println("\n=== Change Password ===");
        String userId = IOUtils.getStringInput("Enter your user ID: ");
        String oldPass = IOUtils.getStringInput("Enter old password: ");
        String newPass = IOUtils.getStringInput("Enter new password: ");
        System.out.println("Password updated successfully for user ID: " + userId);
    }
}
