package com.flipkart.bean;

public class FlipFitPayment {
    private int paymentId;
    private int userId;
    private double amount;
    private String status; // e.g., "SUCCESS", "FAILED", "PENDING"

    public FlipFitPayment() {}

    public FlipFitPayment(int paymentId, int userId, double amount, String status) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.amount = amount;
        this.status = status;
    }

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
