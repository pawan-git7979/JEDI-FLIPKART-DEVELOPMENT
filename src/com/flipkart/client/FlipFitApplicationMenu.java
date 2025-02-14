package com.flipkart.client;

import com.flipkart.io.FlipFitIO;
import com.flipkart.bean.*;
import com.flipkart.business.*;

import java.util.ArrayList;
import java.util.List;

public class FlipFitApplicationMenu {

    // In-memory storage for users and pending gym owner registrations.
    private static List<FlipFitUser> userRepository = new ArrayList<>();
    private static List<FlipFitGymOwner> pendingGymOwnerRegistrations = new ArrayList<>();
    // In-memory storage for gym centers.
    private static List<FlipFitGymCenter> centers = new ArrayList<>();

    // Hard-coded admin credentials.
    private static final String ADMIN_EMAIL = "admin@flipfit.com";
    private static final String ADMIN_PASSWORD = "admin123";
    private static final FlipFitGymAdmin HARD_CODED_ADMIN =
            new FlipFitGymAdmin(1, "AdminUser", ADMIN_EMAIL, ADMIN_PASSWORD, "GymAdmin", new ArrayList<>());

    public static void main(String[] args) {
        // Initialize business services.
        FlipFitAdminService adminService = new FlipFitAdminService();
        FlipFitCustomerService customerService = new FlipFitCustomerService(centers);
        FlipFitBookingService bookingService = new FlipFitBookingService();
        FlipFitGymOwnerService ownerService = new FlipFitGymOwnerService(centers);
        FlipFitPaymentService paymentService = new FlipFitPaymentService();
        FlipFitNotificationService notificationService = new FlipFitNotificationService();

        while (true) {
            System.out.println("\n=== FlipFit Main Menu ===");
            System.out.println("1. Login");
            System.out.println("2. Register (Gym Customer / Gym Owner)");
            System.out.println("3. Exit");
            int choice = FlipFitIO.getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    login(adminService, customerService, bookingService, ownerService,
                          paymentService, notificationService);
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Exiting FlipFit. Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void login(FlipFitAdminService adminService,
                              FlipFitCustomerService customerService,
                              FlipFitBookingService bookingService,
                              FlipFitGymOwnerService ownerService,
                              FlipFitPaymentService paymentService,
                              FlipFitNotificationService notificationService) {
        System.out.println("\n=== Login ===");
        String email = FlipFitIO.getStringInput("Enter email: ");
        String password = FlipFitIO.getStringInput("Enter password: ");

        // Check if admin.
        if (email.equalsIgnoreCase(ADMIN_EMAIL) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("Admin login successful. Welcome, " + HARD_CODED_ADMIN.getName() + "!");
            FlipFitAdminMenu.showMenu(HARD_CODED_ADMIN, pendingGymOwnerRegistrations, userRepository);
            return;
        }

        // Look up in user repository.
        FlipFitUser found = null;
        for (FlipFitUser user : userRepository) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                found = user;
                break;
            }
        }
        if (found == null) {
            System.out.println("Invalid credentials. Login failed.");
            return;
        }
        System.out.println("Login successful. Welcome, " + found.getName() + "!");

        // If GymOwner is still pending approval, block them
        if (found.getRole().equalsIgnoreCase("GymOwner")) {
            boolean pending = false;
            for (FlipFitGymOwner owner : pendingGymOwnerRegistrations) {
                if (owner.getUserId() == found.getUserId()) {
                    pending = true;
                    break;
                }
            }
            if (pending) {
                System.out.println("Your Gym Owner registration is pending approval by admin. Please wait.");
                return;
            }
        }

        // Direct the user to the appropriate menu
        if (found.getRole().equalsIgnoreCase("GymCustomer")) {
            FlipFitCustomerMenu.showMenu((FlipFitGymCustomer) found, centers,
                                         bookingService, customerService,
                                         paymentService, notificationService);
        } else if (found.getRole().equalsIgnoreCase("GymOwner")) {
            FlipFitGymOwnerMenu.showMenu((FlipFitGymOwner) found, ownerService);
        }
    }

    private static void register() {
        System.out.println("\n=== Registration ===");
        System.out.println("Select role to register:");
        System.out.println("1. Gym Customer");
        System.out.println("2. Gym Owner");
        int roleChoice = FlipFitIO.getIntInput("Enter your choice: ");
        String name = FlipFitIO.getStringInput("Enter your name: ");
        String email = FlipFitIO.getStringInput("Enter your email: ");
        String password = FlipFitIO.getStringInput("Enter your password: ");
        if (roleChoice == 1) {
            FlipFitGymCustomer customer = new FlipFitGymCustomer(
                    userRepository.size() + 1, name, email, password, "Gold"
            );
            userRepository.add(customer);
            System.out.println("Gym Customer registered successfully.");
        } else if (roleChoice == 2) {
            FlipFitGymOwner owner = new FlipFitGymOwner(
                    userRepository.size() + 1, name, email, password, "GymOwner",
                    new ArrayList<>()
            );
            userRepository.add(owner);
            pendingGymOwnerRegistrations.add(owner);
            System.out.println("Gym Owner registration submitted and pending admin approval.");
        } else {
            System.out.println("Invalid role choice.");
        }
    }
}
