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
      public void viewBookings(int userId, Scanner scanner) {

        try {
            // Fetch user's bookings
            List<FlipFitBooking> bookings = customerDAO.getUserBookings(userId);
            if (bookings.isEmpty()) {
                throw new CustomerException("No bookings found.");
            }

            System.out.println("\n=== Your Bookings ===");
            for (int i = 0; i < bookings.size(); i++) {
                FlipFitBooking booking = bookings.get(i);
                System.out.printf("| %-3d | Booking ID: %-5d | Gym ID: %-5d | Slot ID: %-5d | Status: %-10s |\n",
                        (i + 1), booking.getBookingId(), booking.getCenterId(), booking.getSlotId(), booking.getStatus());
            }
            System.out.println("=========================================");
            System.out.println("Press 0 to go back, or select a booking to delete (1-" + bookings.size() + "): ");
            
            int choice = FlipFitIOUtils.getIntInput("Enter your choice: ", scanner);
            if (choice == 0) {
                return;
            }

            if (choice < 1 || choice > bookings.size()) {
                throw new CustomerException("Invalid choice. Please try again.");
            }

            FlipFitBooking selectedBooking = bookings.get(choice - 1);
            boolean isDeleted = customerDAO.deleteBooking(selectedBooking.getBookingId());

            if (isDeleted) {
                System.out.println("✅ Booking ID " + selectedBooking.getBookingId() + " has been successfully deleted.");
            } else {
                System.out.println("❌ Failed to delete booking. Please try again.");
            }
        } catch (CustomerException e) {
            System.out.println("⚠️ Error: " + e.getMessage());
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
}

