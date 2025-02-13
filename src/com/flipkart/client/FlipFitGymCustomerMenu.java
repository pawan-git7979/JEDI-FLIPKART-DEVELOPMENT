package com.flipkart.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FlipFitGymCustomerMenu {

    private static List<String> bookings = new ArrayList<>(); // Simulate bookings
    private static Map<String, List<String>> availableSlots = createDummySlots();
    private static Scanner scanner = new Scanner(System.in); // Static scanner for IOUtils

    public static void showMenu(String username) {
        int choice;
        do {
            System.out.println("\n=== Gym Customer Menu ===");
            System.out.println("1. Book Slot");
            System.out.println("2. View Bookings");
            System.out.println("3. View Payment Info");
            System.out.println("4. View Notifications");
            System.out.println("5. Logout");
            choice = getIntInput("Enter your choice: "); // Use getIntInput

            switch (choice) {
                case 1:
                    bookSlot();
                    break;
                case 2:
                    viewBookings();
                    break;
                case 3:
                    viewPaymentInfo();
                    break;
                case 4:
                    System.out.println("List of Notifications...");
                    break;
                case 5:
                	System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    private static Map<String, List<String>> createDummySlots() {
        Map<String, List<String>> slots = new HashMap<>();
        slots.put("Bangalore", new ArrayList<>(List.of("9:00 AM", "10:00 AM", "11:00 AM", "5:00 PM", "6:00 PM")));
        slots.put("Hyderabad", new ArrayList<>(List.of("10:00 AM", "11:00 AM", "12:00 PM", "4:00 PM", "5:00 PM")));
        slots.put("Mumbai", new ArrayList<>(List.of("8:00 AM", "9:00 AM", "10:00 AM", "6:00 PM", "7:00 PM")));

        return slots;
    }

    private static void bookSlot() {
        System.out.println("\n--- Available Locations ---");
        int locationIndex = 1;
        List<String> locationNames = new ArrayList<>(availableSlots.keySet());
        for (String location : locationNames) {
            System.out.println(locationIndex + ". " + location);
            locationIndex++;
        }

        int locationChoice = getIntInput("Enter location number: ");

        if (locationChoice < 1 || locationChoice > locationNames.size()) {
            System.out.println("Invalid location choice.");
            return;
        }

        String chosenLocation = locationNames.get(locationChoice - 1);

        System.out.println("\n--- Available Times at " + chosenLocation + " ---");
        List<String> times = availableSlots.get(chosenLocation);
        for (int i = 0; i < times.size(); i++) {
            System.out.println((i + 1) + ". " + times.get(i));
        }

        int timeChoice = getIntInput("Enter time slot number: ");

        if (timeChoice < 1 || timeChoice > times.size()) {
            System.out.println("Invalid time slot choice.");
            return;
        }

        String chosenTime = times.get(timeChoice - 1);

        bookings.add("Location: " + chosenLocation + ", Timing: " + chosenTime);

        int cardNumber = getIntInput("Enter card number: "); // Placeholder - handle securely in real app
        System.out.println("Payment successful!");
        System.out.println("Notification sent Booked successfully!");
    }

    private static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        System.out.println("\n--- Your Bookings ---");
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println((i + 1) + ". " + bookings.get(i));
        }

        int cancelChoice = getIntInput("Enter booking number to cancel or you can press 0 to go back: ");
        if (cancelChoice > 0 && cancelChoice <= bookings.size()) {
            bookings.remove(cancelChoice - 1);
            System.out.println("Booking cancelled successfully!");
            System.out.println("Notification sent (successfully)");
        } else if (cancelChoice != 0) {
            System.out.println("Invalid booking number.");
        }
    }

    private static void viewPaymentInfo() {
        System.out.println("\n--- Payment Information ---");
        System.out.println("Saved Cards: (Simulated)"); // Replace with actual card info

        int choice;
        do {
            System.out.println("1. View Saved Cards");
            System.out.println("2. Add New Card");
            System.out.println("3. Go Back");
            choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    System.out.println("Viewing Saved Cards..."); // Implement viewing logic
                    break;
                case 2:
                    System.out.println("Adding New Card..."); // Implement adding logic
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }


    public static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = scanner.nextInt();
                scanner.nextLine(); // Consume newline left by nextInt()
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            }
        }
    }


}
