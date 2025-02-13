package com.flipkart.client;

public class LoginIO {
    public static void login() {
        System.out.println("\n=== Login ===");
        String username = IOUtils.getStringInput("Enter username: ");
        String password = IOUtils.getStringInput("Enter password: ");
        String role = IOUtils.getStringInput("Enter role (gym customer / gym owner / gym admin): ");

        switch (role.toLowerCase()) {
            case "gym customer":
                GymCustomerMenu.showMenu(username);
                break;
            case "gym owner":
                GymOwnerMenu.showMenu(username);
                break;
            case "gym admin":
                GymAdminMenu.showMenu(username);
                break;
            default:
                System.out.println("Unrecognized role, returning to main menu.");
                break;
        }
    }
}
