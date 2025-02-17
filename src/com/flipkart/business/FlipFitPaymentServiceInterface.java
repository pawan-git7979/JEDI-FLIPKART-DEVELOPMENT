package com.flipkart.business;

import java.util.Scanner;

public interface FlipFitPaymentServiceInterface {
    void processPayment(Scanner scanner, int userId, int bookingId, int price);
}
