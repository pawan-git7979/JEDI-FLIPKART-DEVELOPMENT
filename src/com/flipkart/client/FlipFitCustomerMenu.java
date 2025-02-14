package com.flipkart.client;

import com.flipkart.io.FlipFitIO;
import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitGymCustomer;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitGymSlot;
import com.flipkart.bean.FlipFitNotification;
import com.flipkart.business.FlipFitBookingService;
import com.flipkart.business.FlipFitCustomerService;
import com.flipkart.business.FlipFitNotificationService;
import com.flipkart.business.FlipFitPaymentService;
import java.util.ArrayList;
import java.util.List;

public class FlipFitCustomerMenu {

    // In-memory lists for saved cards and notifications (simulate payment info and notifications)
    private static List<String> savedCards = new ArrayList<>();
    private static List<FlipFitNotification> notifications = new ArrayList<>();

    // References to shared objects (set from main)
    private static List<FlipFitGymCenter> centers;
    private static FlipFitBookingService bookingService;
    private static FlipFitCustomerService customerService;
    private static FlipFitPaymentService paymentService;
    private static FlipFitNotificationService notificationService;

    public static void showMenu(FlipFitGymCustomer customer, List<FlipFitGymCenter> centers,
                                FlipFitBookingService bookingService,
                                FlipFitCustomerService customerService,
                                FlipFitPaymentService paymentService,
                                FlipFitNotificationService notificationService) {
        FlipFitCustomerMenu.centers = centers;
        FlipFitCustomerMenu.bookingService = bookingService;
        FlipFitCustomerMenu.customerService = customerService;
        FlipFitCustomerMenu.paymentService = paymentService;
        FlipFitCustomerMenu.notificationService = notificationService;

        int choice;
        do {
            System.out.println("\n=== FlipFit Customer Menu ===");
            System.out.println("1. Book Slot");
            System.out.println("2. View Bookings");
            System.out.println("3. View Payment Info");
            System.out.println("4. View Notifications");
            System.out.println("5. Logout");
            choice = FlipFitIO.getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    bookSlot(customer);
                    break;
                case 2:
                    viewBookings(customer);
                    break;
                case 3:
                    viewPaymentInfo(customer);
                    break;
                case 4:
                    viewNotifications(customer);
                    break;
                case 5:
                    System.out.println("Logging out from Customer account...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private static void bookSlot(FlipFitGymCustomer customer) {
        // 1. Gather all distinct locations from available gym centers
        if (centers.isEmpty()) {
            System.out.println("No Gym Centers have been added by Gym Owners yet.");
            return;
        }
        List<String> distinctCities = new ArrayList<>();
        for (FlipFitGymCenter gc : centers) {
            String city = gc.getLocation();
            if (!distinctCities.contains(city)) {
                distinctCities.add(city);
            }
        }
        if (distinctCities.isEmpty()) {
            System.out.println("No locations available yet.");
            return;
        }
        // 2. Display numeric menu for locations
        System.out.println("\n--- Available Locations ---");
        for (int i = 0; i < distinctCities.size(); i++) {
            System.out.println((i + 1) + ". " + distinctCities.get(i));
        }
        int cityChoice = FlipFitIO.getIntInput("Select a location by number (or 0 to cancel): ");
        if (cityChoice < 1 || cityChoice > distinctCities.size()) {
            System.out.println("Booking cancelled.");
            return;
        }
        String chosenCity = distinctCities.get(cityChoice - 1);

        // 3. Gather all centers in the chosen location
        List<FlipFitGymCenter> centersInCity = new ArrayList<>();
        for (FlipFitGymCenter center : centers) {
            if (center.getLocation().equalsIgnoreCase(chosenCity)) {
                centersInCity.add(center);
            }
        }
        if (centersInCity.isEmpty()) {
            System.out.println("No gym centers available in " + chosenCity);
            return;
        }
        System.out.println("\n--- Gym Centers in " + chosenCity + " ---");
        for (int i = 0; i < centersInCity.size(); i++) {
            FlipFitGymCenter c = centersInCity.get(i);
            System.out.println((i + 1) + ". Center ID: " + c.getId() + " | Name: " + c.getName());
        }
        int centerChoice = FlipFitIO.getIntInput("Select a Gym Center by number (or 0 to cancel): ");
        if (centerChoice < 1 || centerChoice > centersInCity.size()) {
            System.out.println("Booking cancelled.");
            return;
        }
        FlipFitGymCenter selectedCenter = centersInCity.get(centerChoice - 1);

        // 4. Display available slots for the chosen center, including timings.
        List<FlipFitGymSlot> slots = selectedCenter.getSlots();
        if (slots == null || slots.isEmpty()) {
            System.out.println("No slots available at " + selectedCenter.getName() + " at the moment.");
            return;
        }
        System.out.println("\n--- Available Slots for " + selectedCenter.getName() + " ---");
        for (int i = 0; i < slots.size(); i++) {
            FlipFitGymSlot slot = slots.get(i);
            System.out.println((i + 1) + ". Slot ID: " + slot.getSlotId() + " | Time: " + slot.getStartTime() + " - " + slot.getEndTime());
        }
        int slotChoice = FlipFitIO.getIntInput("Select a Slot by number (or 0 to cancel): ");
        if (slotChoice < 1 || slotChoice > slots.size()) {
            System.out.println("Booking cancelled.");
            return;
        }
        FlipFitGymSlot chosenSlot = slots.get(slotChoice - 1);

        // 5. Payment details: Ask for card info
        System.out.println("\n--- Payment ---");
        String card = FlipFitIO.getStringInput("Enter card number: ");
        savedCards.add(card);
        System.out.println("Card details saved.");
        double amount = 100.00;
        if (paymentService.processPayment(customer, amount)) {
            // 6. Create booking and generate detailed confirmation message
            FlipFitBooking booking = bookingService.createBooking(customer, selectedCenter.getId(), chosenSlot.getSlotId(), 1);
            String successMsg = "Booking Confirmed!\n"
                    + "Booking ID: " + booking.getId() + "\n"
                    + "Customer: " + customer.getName() + "\n"
                    + "Gym Center: " + selectedCenter.getName() + " (" + selectedCenter.getLocation() + ")\n"
                    + "Slot: " + chosenSlot.getSlotId() + " (" + chosenSlot.getStartTime() + " - " + chosenSlot.getEndTime() + ")\n"
                    + "Amount Paid: $" + amount;
            notificationService.sendNotification(customer, successMsg);
            notifications.add(new FlipFitNotification(booking.getId(), customer.getUserId(), successMsg));
            System.out.println(successMsg);
        } else {
            System.out.println("Payment failed or cancelled. No booking was created.");
        }
    }

    private static void viewBookings(FlipFitGymCustomer customer) {
        System.out.println("\n--- Your Bookings ---");
        List<FlipFitBooking> allBookings = bookingService.getAllBookings();
        List<FlipFitBooking> myBookings = new ArrayList<>();
        for (FlipFitBooking booking : allBookings) {
            if (booking.getUserId() == customer.getUserId() && booking.getStatus().equalsIgnoreCase("BOOKED")) {
                myBookings.add(booking);
            }
        }
        if (myBookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        for (int i = 0; i < myBookings.size(); i++) {
            System.out.println((i + 1) + ". Booking ID: " + myBookings.get(i).getId()
                    + " | Center ID: " + myBookings.get(i).getCenterId()
                    + " | Slot ID: " + myBookings.get(i).getSlotId());
        }
        int cancelChoice = FlipFitIO.getIntInput("Enter booking number to cancel (or 0 to go back): ");
        if (cancelChoice > 0 && cancelChoice <= myBookings.size()) {
            int bookingId = myBookings.get(cancelChoice - 1).getId();
            bookingService.cancelBooking(customer, bookingId);
            notificationService.sendNotification(customer, "Booking cancelled with ID: " + bookingId);
        } else if (cancelChoice != 0) {
            System.out.println("Invalid booking number.");
        }
    }

    private static void viewPaymentInfo(FlipFitGymCustomer customer) {
        int choice;
        do {
            System.out.println("\n--- Payment Information ---");
            System.out.println("1. View Saved Cards");
            System.out.println("2. Add New Card");
            System.out.println("3. Go Back");
            choice = FlipFitIO.getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    if (savedCards.isEmpty()) {
                        System.out.println("No saved cards.");
                    } else {
                        System.out.println("Saved Cards:");
                        for (String card : savedCards) {
                            System.out.println(card);
                        }
                    }
                    break;
                case 2:
                    String card = FlipFitIO.getStringInput("Enter new card number: ");
                    savedCards.add(card);
                    System.out.println("New card added successfully.");
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }

    private static void viewNotifications(FlipFitGymCustomer customer) {
        System.out.println("\n--- Your Notifications ---");
        boolean anyNotif = false;
        for (FlipFitNotification notif : notifications) {
            if (notif.getUserId() == customer.getUserId()) {
                System.out.println("Notification ID: " + notif.getNotificationId() + " | Message: " + notif.getMessage());
                anyNotif = true;
            }
        }
        if (!anyNotif) {
            System.out.println("No notifications available.");
        }
    }
}
