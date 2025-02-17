package com.flipkart.business;

import com.flipkart.dao.FlipFitCustomer.FlipFitCustomerImpl;
import com.flipkart.dao.FlipFitCustomer.FlipFitCustomerInterface;
import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitPayment;
import com.flipkart.bean.FlipFitNotification;
import com.flipkart.utils.FlipFitIOUtils;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitGymSlot;
import com.flipkart.exception.SlotNotAvailableException;  // Import custom exception for slot
import com.flipkart.exception.PaymentFailureException;  // Import custom exception for payment
import java.util.List;
import java.util.Scanner;

public class FlipFitCustomerService {
    private FlipFitCustomerInterface customerDAO = new FlipFitCustomerImpl();
    private FlipFitPaymentService paymentService = new FlipFitPaymentService();

    // Book a slot
    public void bookSlot(Scanner scanner, int userId) {
        List<String> cities = customerDAO.getAvailableCities();
        if (cities.isEmpty()) {
            System.out.println("No gyms available currently.");
            return;
        }

        int cityChoice = FlipFitIOUtils.getChoice(scanner, "Available Cities", cities);
        String selectedCity = cities.get(cityChoice - 1);

        // Fetch gyms
        List<FlipFitGymCenter> gyms = customerDAO.getGymsByCity(selectedCity);
        if (gyms.isEmpty()) {
            System.out.println("No gyms found in this city.");
            return;
        }

        int gymChoice = FlipFitIOUtils.getChoice(scanner, "Available Gyms", gyms);
        FlipFitGymCenter selectedGym = gyms.get(gymChoice - 1);

        try {
            // Fetch slots
            List<FlipFitGymSlot> slots = customerDAO.getAvailableSlots(selectedGym.getId());
            if (slots.isEmpty()) {
                System.out.println("No slots available. Adding to waitlist...");

                // Add user to waitlist
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
        } catch (SlotNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());  // Handle SlotNotAvailableException
        } catch (PaymentFailureException e) {
            System.out.println("Error: " + e.getMessage());  // Handle PaymentFailureException
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());  // General error handling
        }
    }

    // View all bookings of a customer
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

    // View payment information
    public void viewPayments(int userId) {
        try {
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
        } catch (PaymentFailureException e) {
            System.out.println("Error: " + e.getMessage());  // Handle PaymentFailureException
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());  // General error handling
        }
    }

    // View notifications
    public void viewNotifications(int userId) {
        try {
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
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());  // General error handling
        }
    }
}
