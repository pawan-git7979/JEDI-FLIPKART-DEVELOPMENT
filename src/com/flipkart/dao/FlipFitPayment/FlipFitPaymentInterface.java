package com.flipkart.dao.FlipFitPayment;

import com.flipkart.bean.FlipFitPayment;

/**
 * Interface defining the operations for managing payments in the FlipFit system.
 */
public interface FlipFitPaymentInterface {

    /**
     * Processes a payment for a user.
     *
     * @param payment The FlipFitPayment object containing the payment details.
     * @return true if the payment is successfully processed, false otherwise.
     */
    boolean processPayment(FlipFitPayment payment);

    /**
     * Retrieves the payment details for a specific payment ID.
     *
     * @param paymentId The ID of the payment whose details need to be fetched.
     * @return A FlipFitPayment object containing the payment details, or null if the payment is not found.
     */
    FlipFitPayment getPaymentDetails(int paymentId);

    /**
     * Sends a notification to the user regarding their booking and payment details.
     *
     * @param userId The ID of the user to be notified.
     * @param bookingId The ID of the booking related to the payment.
     * @param price The total price of the booking.
     */
    void sendNotification(int userId, int bookingId, int price);
}
