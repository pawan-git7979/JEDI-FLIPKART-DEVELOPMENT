package com.flipkart.business;

import java.util.Scanner;

public class FlipFitPaymentService {
    private FlipFitPaymentDAO paymentDAO = new FlipFitPaymentDAO();

    public void processPayment(Scanner scanner, int userId, int bookingId) {
        System.out.println("Enter payment amount:");
        double amount = scanner.nextDouble();

        boolean paymentSuccess = paymentDAO.makePayment(userId, bookingId, amount);
        if (paymentSuccess) {
            System.out.println("Payment successful! Booking confirmed.");
        } else {
            System.out.println("Payment failed. Please try again.");
        }
    }
}
