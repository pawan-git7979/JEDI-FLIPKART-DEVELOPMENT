package com.flipkart.business;

import com.flipkart.bean.FlipFitPayment;
import com.flipkart.dao.FlipFitPayment.FlipFitPaymentImpl;
import com.flipkart.dao.FlipFitPayment.FlipFitPaymentInterface;
import com.flipkart.io.FlipFitIO;
import java.util.Scanner;

public class FlipFitPaymentService {
    private FlipFitPaymentInterface paymentDAO = new FlipFitPaymentImpl();

    public void processPayment(Scanner scanner, int userId, int bookingId, int price) {
        System.out.println("Amount to be paid : " +price);
//        System.out.println("");

//        double amount = scanner.nextDouble();

//        scanner.nextLine(); // Consume newline
        String userInput= FlipFitIO.getStringInput("Proceed to payment? (y/n)");
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

        boolean paymentSuccess = paymentDAO.processPayment(payment);
        if (paymentSuccess) {
            paymentDAO.sendNotification(userId, bookingId, price);
            System.out.println("Payment successful! Booking confirmed.");
        } else {
            System.out.println("Payment failed. Please try again.");
        }
    }

}
