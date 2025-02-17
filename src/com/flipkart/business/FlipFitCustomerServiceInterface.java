package com.flipkart.business;

import java.util.Scanner;

/**
 * Interface defining the services available to a FlipFit customer.
 * This interface provides methods for booking gym slots, viewing bookings,
 * payments, and notifications.
 */
public interface FlipFitCustomerServiceInterface {

    /**
     * Method for booking a gym slot.
     * Allows the customer to choose a city, gym, and available slot for booking.
     *
     * @param scanner The scanner used to capture user input.
     * @param userId The ID of the customer who is booking the slot.
     */
    void bookSlot(Scanner scanner, int userId);

    void viewBookings(int userId, Scanner scanner);

    void viewPayments(int userId);

    /**
     * Method for viewing the notifications related to the customer.
     * Displays any notifications associated with the customer's account.
     *
     * @param userId The ID of the customer whose notifications are to be viewed.
     */
    void viewNotifications(int userId);
    
}
