package com.flipkart.business;

import com.flipkart.bean.FlipFitPayment;
import com.flipkart.dao.FlipFitPayment.FlipFitPaymentImpl;
import com.flipkart.dao.FlipFitPayment.FlipFitPaymentInterface;

import java.util.Scanner;

public class FlipFitPaymentService {
    private FlipFitPaymentInterface paymentDAO = new FlipFitPaymentImpl();

    public void processPayment(Scanner scanner, int userId, int bookingId) {
        System.out.println("Enter payment amount:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        FlipFitPayment payment = new FlipFitPayment();
        payment.setUserId(userId);
        payment.setAmount(amount);
        payment.setStatus("PENDING");

        boolean paymentSuccess = paymentDAO.processPayment(payment);
        if (paymentSuccess) {
            System.out.println("Payment successful! Booking confirmed.");
        } else {
            System.out.println("Payment failed. Please try again.");
        }
    }
}
