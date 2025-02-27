package com.flipkart.business;

import com.flipkart.bean.FlipFitPayment;
import com.flipkart.dao.FlipFitPayment.FlipFitPaymentInterface;
import com.flipkart.dao.FlipFitPayment.FlipFitPaymentImpl;
import com.flipkart.io.FlipFitIO;
import java.util.Scanner;

/**
 * Service implementation for handling payment processing in the FlipFit system.
 * This service processes payments for bookings, sends notifications, and handles success or failure of transactions.
 */
public class FlipFitPaymentServiceImpl implements FlipFitPaymentServiceInterface {

    private FlipFitPaymentInterface paymentDAO = new FlipFitPaymentImpl();

    /**
     * Processes payment for a booking made by the user.
     *
     * @param scanner The scanner used to capture user input.
     * @param userId The ID of the user making the payment.
     * @param bookingId The ID of the booking for which the payment is being made.
     * @param price The price of the booking (amount to be paid).
     */
    @Override
    public void processPayment(Scanner scanner, int userId, int bookingId, int price) {
        // Displaying the amount to be paid
        System.out.println("Amount to be paid: ₹" + price);

        // Asking the user to confirm if they want to proceed with the payment
        String userInput = FlipFitIO.getStringInput("Proceed to payment? (y/n)");
        if (userInput.equalsIgnoreCase("y")) {
            // Creating a new payment object and setting payment details
            FlipFitPayment payment = new FlipFitPayment();
            payment.setUserId(userId);
            payment.setAmount(price);
            payment.setStatus("SUCCESS");

            // Processing the payment using the paymentDAO
            boolean paymentSuccess = paymentDAO.processPayment(payment);
            if (paymentSuccess) {
                // Sending notification and confirming booking if payment is successful
                paymentDAO.sendNotification(userId, bookingId, price);
                System.out.println("Payment successful! Booking confirmed.");
            } else {
                // Displaying failure message if payment did not succeed
                System.out.println("Payment failed. Please try again.");
            }
        } else {
            // If user cancels the payment, the booking is cancelled
            System.out.println("Payment cancelled. Booking cancelled.");
        }
    }
}
