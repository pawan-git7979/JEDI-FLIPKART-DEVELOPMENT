package com.flipkart.business;

import com.flipkart.bean.FlipFitPayment;
import com.flipkart.dao.FlipFitPayment.FlipFitPaymentImpl;
import com.flipkart.dao.FlipFitPayment.FlipFitPaymentInterface;
import com.flipkart.exception.PaymentFailureException;
import com.flipkart.io.FlipFitIO;
import java.util.Scanner;

public class FlipFitPaymentService {
    private FlipFitPaymentInterface paymentDAO = new FlipFitPaymentImpl();

    public void processPayment(Scanner scanner, int userId, int bookingId, int price) {
        System.out.println("Amount to be paid: ₹" + price);

        String userInput = FlipFitIO.getStringInput("Proceed to payment? (y/n)");
        switch (userInput) {
            case "y":
                break;
            case "n":
                System.out.println("Payment cancelled. Booking cancelled.");
                return;
            default:
                System.out.println("Invalid input. Payment cancelled. Booking cancelled.");
                return;
        }

        FlipFitPayment payment = new FlipFitPayment();
        payment.setUserId(userId);
        payment.setAmount(price);
        payment.setStatus("SUCCESS");

        try {
            // Process the payment
            boolean paymentSuccess = paymentDAO.processPayment(payment);
            if (paymentSuccess) {
                // Send notification to the user
                paymentDAO.sendNotification(userId, bookingId, price);
                System.out.println("Payment successful! Booking confirmed.");
            } else {
                System.out.println("Payment failed. Please try again.");
            }
        } catch (PaymentFailureException e) {
            // Handle PaymentFailureException specifically
            System.out.println("Payment failed: " + e.getMessage());
            System.out.println("Please try again later or contact support if the issue persists.");
        } catch (Exception e) {
            // General exception handling for unexpected errors
            System.out.println("An unexpected error occurred: " + e.getMessage());
            System.out.println("Please try again later.");
        }
    }
}
