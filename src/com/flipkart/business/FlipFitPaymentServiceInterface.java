package com.flipkart.business;

import java.util.Scanner;

/**
 * Interface defining the contract for payment processing services in the FlipFit system.
 * The service is responsible for handling the payment process, including the payment confirmation and transaction.
 */
public interface FlipFitPaymentServiceInterface {

    /**
     * Processes the payment for a user's booking.
     *
     * @param scanner The scanner used to capture user input during payment.
     * @param userId The ID of the user making the payment.
     * @param bookingId The ID of the booking being paid for.
     * @param price The total amount to be paid for the booking.
     */
    void processPayment(Scanner scanner, int userId, int bookingId, int price);
}
