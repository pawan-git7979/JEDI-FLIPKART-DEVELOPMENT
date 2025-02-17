package com.flipkart.bean;

/**
 * The FlipFitPayment class represents a payment made by a user.
 * It contains details such as the payment ID, user ID, amount, and payment status.
 */
public class FlipFitPayment {
    private int paymentId; // Unique identifier for the payment
    private int userId; // User ID who made the payment
    private double amount; // Amount of the payment
    private String status; // Status of the payment (e.g., "success", "failed")

    /**
     * Default Constructor.
     * Initializes a new FlipFitPayment object without setting any fields.
     */
    public FlipFitPayment() {}

    /**
     * Parameterized Constructor.
     * Initializes a new FlipFitPayment object with the specified details.
     *
     * @param paymentId The unique ID of the payment.
     * @param userId    The user ID who made the payment.
     * @param amount    The amount of the payment.
     * @param status    The status of the payment (e.g., "success", "failed").
     */
    public FlipFitPayment(int paymentId, int userId, double amount, String status) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.amount = amount;
        this.status = status;
    }

    /**
     * Gets the unique payment ID.
     *
     * @return The payment ID.
     */
    public int getPaymentId() { return paymentId; }

    /**
     * Sets the unique payment ID.
     *
     * @param paymentId The payment ID.
     */
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    /**
     * Gets the user ID who made the payment.
     *
     * @return The user ID.
     */
    public int getUserId() { return userId; }

    /**
     * Sets the user ID who made the payment.
     *
     * @param userId The user ID.
     */
    public void setUserId(int userId) { this.userId = userId; }

    /**
     * Gets the amount of the payment.
     *
     * @return The amount of the payment.
     */
    public double getAmount() { return amount; }

    /**
     * Sets the amount of the payment.
     *
     * @param amount The amount of the payment.
     */
    public void setAmount(double amount) { this.amount = amount; }

    /**
     * Gets the status of the payment (e.g., "success", "failed").
     *
     * @return The status of the payment.
     */
    public String getStatus() { return status; }

    /**
     * Sets the status of the payment (e.g., "success", "failed").
     *
     * @param status The status of the payment.
     */
    public void setStatus(String status) { this.status = status; }
}
