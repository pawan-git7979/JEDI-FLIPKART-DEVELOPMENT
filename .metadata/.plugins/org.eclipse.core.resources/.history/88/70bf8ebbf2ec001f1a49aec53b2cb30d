package com.flipkart.business;

import com.flipkart.dao.FlipFitCustomer.FlipFitCustomerInterface;
import com.flipkart.dao.FlipFitCustomer.FlipFitCustomerImpl;
import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitPayment;
import com.flipkart.bean.FlipFitNotification;
import com.flipkart.utils.FlipFitIOUtils;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitGymSlot;
import java.util.List;
import java.util.Scanner;

public class FlipFitCustomerServiceImpl implements FlipFitCustomerServiceInterface {
    private FlipFitCustomerInterface customerDAO = new FlipFitCustomerImpl();
    private FlipFitPaymentServiceInterface paymentService = new FlipFitPaymentServiceImpl();

    @Override
    public void bookSlot(Scanner scanner, int userId) {
        List<String> cities = customerDAO.getAvailableCities();
        if (cities.isEmpty()) {
            System.out.println("No gyms available currently.");
            return;
        }

        // Display cities in a table format
        System.out.println("\n===============================");
        System.out.println("|      Available Cities       |");
        System.out.println("===============================");
        System.out.printf("| %-3s | %-25s |\n", "No.", "City Name");
        System.out.println("-------------------------------");

        for (int i = 0; i < cities.size(); i++) {
            System.out.printf("| %-3d | %-25s |\n", i + 1, cities.get(i));
        }
        System.out.println("===============================");

        int cityChoice = FlipFitIOUtils.getIntInput("Enter your choice (1-" + cities.size() + "): ", scanner);

        if (cityChoice < 1 || cityChoice > cities.size()) {
            System.out.println("Invalid choice. Returning to main menu.");
            return;
        }
        String selectedCity = cities.get(cityChoice - 1);

        // Fetch gyms
        List<FlipFitGymCenter> gyms = customerDAO.getGymsByCity(selectedCity);
        if (gyms.isEmpty()) {
            System.out.println("No gyms found in this city.");
            return;
        }

        // Display gyms in a table format
        System.out.println("\n===============================");
        System.out.println("|        Available Gyms       |");
        System.out.println("===============================");
        System.out.printf("| %-3s | %-25s |\n", "No.", "Gym Name");
        System.out.println("-------------------------------");

        for (int i = 0; i < gyms.size(); i++) {
            System.out.printf("| %-3d | %-25s |\n", i + 1, gyms.get(i).getName());
        }
        System.out.println("===============================");

        // Use getIntInput to get the gym choice
        int gymChoice = FlipFitIOUtils.getIntInput("Enter your choice (1-" + gyms.size() + "): ", scanner);

        if (gymChoice < 1 || gymChoice > gyms.size()) {
            System.out.println("Invalid choice. Returning to main menu.");
            return;
        }

        FlipFitGymCenter selectedGym = gyms.get(gymChoice - 1);






        // Fetch slots
        List<FlipFitGymSlot> slots = customerDAO.getAvailableSlots(selectedGym.getId());
        if (slots.isEmpty()) {
            System.out.println("No slots available. Adding to waitlist...");
            customerDAO.addToWaitlist(userId, selectedGym.getId());
            System.out.println("You have been added to the waitlist for Gym ID: " + selectedGym.getId());
            return;
        }

        int slotChoice = FlipFitIOUtils.getChoice(scanner, "Available Slots", slots);
        FlipFitGymSlot selectedSlot = slots.get(slotChoice - 1);

        // Proceed with normal slot booking
        FlipFitBooking booking = customerDAO.bookSlot(userId, selectedGym.getId(), selectedSlot.getSlotId(), 1);
        int slotPrice = selectedSlot.getPrice();

        if (booking != null) {
            System.out.println("Slot chosen successfully! Proceeding to payment...");
            paymentService.processPayment(scanner, userId, booking.getBookingId(), slotPrice);
        }
    }

    @Override
    public void viewBookings(int userId) {
        List<FlipFitBooking> bookings = customerDAO.getUserBookings(userId);
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        System.out.println("\n=== Your Bookings ===");
        for (FlipFitBooking booking : bookings) {
            System.out.println("Booking ID: " + booking.getBookingId());
            System.out.println("Gym ID: " + booking.getCenterId());
            System.out.println("Slot ID: " + booking.getSlotId());
            System.out.println("Status: " + booking.getStatus());
            System.out.println("---------------------------");
        }
    }

    @Override
    public void viewPayments(int userId) {
        List<FlipFitPayment> payments = customerDAO.getUserPayments(userId);
        if (payments.isEmpty()) {
            System.out.println("No payment records found.");
            return;
        }

        System.out.println("\n=== Payment History ===");
        for (FlipFitPayment payment : payments) {
            System.out.println("Payment ID: " + payment.getPaymentId());
            System.out.println("Amount: ₹" + payment.getAmount());
            System.out.println("Status: " + payment.getStatus());
            System.out.println("---------------------------");
        }
    }

    @Override
    public void viewNotifications(int userId) {
        List<FlipFitNotification> notifications = customerDAO.getUserNotifications(userId);
        if (notifications.isEmpty()) {
            System.out.println("No notifications found.");
            return;
        }

        System.out.println("\n=== Notifications ===");
        for (FlipFitNotification notification : notifications) {
            System.out.println("Message: " + notification.getMessage());
            System.out.println("Status: " + notification.getStatus());
            System.out.println("---------------------------");
        }
    }
}
