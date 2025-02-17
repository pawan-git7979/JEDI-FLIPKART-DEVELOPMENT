package com.flipkart.client;

public class LoginIO {
    public static void login() {
        System.out.println("\n=== Login ===");
        String username = IOUtils.getStringInput("Enter username: ");
        String password = IOUtils.getStringInput("Enter password: "); // Consider handling password securely

        System.out.println("Select role:");
        System.out.println("1. Gym Customer");
        System.out.println("2. Gym Owner");
        System.out.println("3. Gym Admin");

        int roleChoice = IOUtils.getIntInput("Enter role number (1-3): ");

        switch (roleChoice) {
            case 1:
                FlipFitGymCustomerMenu.showMenu(username);
                break;
            case 2:
               // FlipFitGymOwnerMenu.showMenu(username);
                break;
            case 3:
                FlipFitGymAdminMenu.showMenu(username);
                break;
            default:
                System.out.println("Invalid role choice, returning to main menu.");
                break;
        }
    }
}