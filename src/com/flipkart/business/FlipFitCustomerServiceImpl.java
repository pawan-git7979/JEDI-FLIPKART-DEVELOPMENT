package com.flipkart.business;

import com.flipkart.dao.FlipFitCustomer.FlipFitCustomerInterface;
import com.flipkart.dao.FlipFitCustomer.FlipFitCustomerImpl;
import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitPayment;
import com.flipkart.bean.FlipFitNotification;
import com.flipkart.utils.FlipFitIOUtils;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitGymSlot;
import com.flipkart.exception.CustomerException;

import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

/**
 * Implementation of customer service for FlipFit, handling booking, payment, viewing bookings,
 * payments, and notifications for a gym customer.
 */
public class FlipFitCustomerServiceImpl implements FlipFitCustomerServiceInterface {
    private FlipFitCustomerInterface customerDAO = new FlipFitCustomerImpl();
    private FlipFitPaymentServiceInterface paymentService = new FlipFitPaymentServiceImpl();

    /**
     * Method to allow a customer to book a gym slot.
     * The customer selects a city, gym, and slot to book. If no slots are available, the customer
     * is added to a waitlist.
     *
     * @param scanner The scanner used to capture user input.
     * @param userId The user ID of the customer making the booking.
     */
    @Override
    public void bookSlot(Scanner scanner, int userId) {
        try {
            // Get available cities for the customer to choose from
            List<String> cities = customerDAO.getAvailableCities();
            if (cities.isEmpty()) {
                throw new CustomerException("No gyms available currently.");
            }

            // Display cities in a formatted table
            System.out.println("\n===============================");
            System.out.println("|      Available Cities       |");
            System.out.println("===============================");
            System.out.printf("| %-3s | %-25s |\n", "No.", "City Name");
            System.out.println("-------------------------------");

            for (int i = 0; i < cities.size(); i++) {
                System.out.printf("| %-3d | %-25s |\n", i + 1, cities.get(i));
            }
            System.out.println("===============================");

            // Get city choice from the user
            int cityChoice = FlipFitIOUtils.getIntInput("Enter your choice (1-" + cities.size() + "): ", scanner);

            if (cityChoice < 1 || cityChoice > cities.size()) {
                throw new CustomerException("Invalid city choice. Please try again.");
            }
            String selectedCity = cities.get(cityChoice - 1);

            // Fetch gyms in the selected city
            List<FlipFitGymCenter> gyms = customerDAO.getGymsByCity(selectedCity);
            if (gyms.isEmpty()) {
                throw new CustomerException("No gyms found in this city.");
            }

            // Display gyms in a formatted table
            System.out.println("\n===============================");
            System.out.println("|        Available Gyms       |");
            System.out.println("===============================");
            System.out.printf("| %-3s | %-25s |\n", "No.", "Gym Name");
            System.out.println("-------------------------------");

            for (int i = 0; i < gyms.size(); i++) {
                System.out.printf("| %-3d | %-25s |\n", i + 1, gyms.get(i).getName());
            }
            System.out.println("===============================");

            // Get gym choice from the user
            int gymChoice = FlipFitIOUtils.getIntInput("Enter your choice (1-" + gyms.size() + "): ", scanner);

            if (gymChoice < 1 || gymChoice > gyms.size()) {
                throw new CustomerException("Invalid gym choice. Please try again.");
            }

            FlipFitGymCenter selectedGym = gyms.get(gymChoice - 1);

            // Fetch available slots for the selected gym
            List<FlipFitGymSlot> slots = customerDAO.getAvailableSlots(selectedGym.getId());
            if (slots.isEmpty()) {
                customerDAO.addToWaitlist(userId, selectedGym.getId());
                throw new CustomerException("No slots available. You have been added to the waitlist for Gym ID: " + selectedGym.getId());
            }

            // Get slot choice from the user
            int slotChoice = FlipFitIOUtils.getChoice(scanner, "Available Slots", slots);
            FlipFitGymSlot selectedSlot = slots.get(slotChoice - 1);

            // Proceed with the booking
            FlipFitBooking booking = customerDAO.bookSlot(userId, selectedGym.getId(), selectedSlot.getSlotId(), 1);
            int slotPrice = selectedSlot.getPrice();

            if (booking != null) {
                System.out.println("Slot chosen successfully! Proceeding to payment...");
                paymentService.processPayment(scanner, userId, booking.getBookingId(), slotPrice);
            } else {
                throw new CustomerException("Booking failed. Please try again.");
            }
        } catch (CustomerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Method to allow a customer to view their past bookings.
     *
     * @param userId The user ID of the customer.
     */
    @Override
    public void viewBookings(int userId) {
        try {
            // Fetch user's bookings
            List<FlipFitBooking> bookings = customerDAO.getUserBookings(userId);
            if (bookings.isEmpty()) {
                throw new CustomerException("No bookings found.");
            }

            System.out.println("\n=== Your Bookings ===");
            bookings.forEach(booking -> {
                System.out.println("Booking ID: " + booking.getBookingId());
                System.out.println("Gym ID: " + booking.getCenterId());
                System.out.println("Slot ID: " + booking.getSlotId());
                System.out.println("Status: " + booking.getStatus());
                System.out.println("---------------------------");
            });
        } catch (CustomerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Method to allow a customer to view their payment history.
     *
     * @param userId The user ID of the customer.
     */
    @Override
    public void viewPayments(int userId) {
        try {
            // Fetch user's payment history
            List<FlipFitPayment> payments = customerDAO.getUserPayments(userId);
            if (payments.isEmpty()) {
                throw new CustomerException("No payment records found.");
            }

            System.out.println("\n=== Payment History ===");
            payments.forEach(payment -> {
                System.out.println("Payment ID: " + payment.getPaymentId());
                System.out.println("Amount: ₹" + payment.getAmount());
                System.out.println("Status: " + payment.getStatus());
                System.out.println("---------------------------");
            });
        } catch (CustomerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Method to allow a customer to view their notifications.
     *
     * @param userId The user ID of the customer.
     */
    @Override
    public void viewNotifications(int userId) {

        try {
            // Fetch user's notifications
            List<FlipFitNotification> notifications = customerDAO.getUserNotifications(userId);
            if (notifications.isEmpty()) {
                throw new CustomerException("No notifications found.");
            }

            System.out.println("\n=== Notifications ===");
            notifications.forEach(notification -> {
                System.out.println("Message: " + notification.getMessage());
                System.out.println("Status: " + notification.getStatus());
                System.out.println("---------------------------");
            });
        } catch (CustomerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Hashtable ht = new Hashtable<String, String>(); // Error 1: Incorrect instantiation

        ht.put("chec", "check");
        ht.put(1000, "check"); // Error 2: Inserting Integer key into Hashtable<String, String>
        ht.put("check", 20.01); // Error 3: Inserting Double into Hashtable<String, String>

        System.out.print(ht.get("chec") + "");
        System.out.print(ht.get(1000) + ""); // Error 4: Inconsistent key types

        System.out.print(ht.get("check"));
    }
}
