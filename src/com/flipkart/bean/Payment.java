package com.flipkart.bean;

import java.util.Date;

public class Payment {
    private String id;
    private String userId;
    private double amount;
    private String paymentMethod;
    private String paymentStatus;
    private Date paymentDate;
    private String transactionStatus;

    public Payment(String id, String userId, double amount, String paymentMethod,
                   String paymentStatus, Date paymentDate, String transactionStatus) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.transactionStatus = transactionStatus;
    }

    public void completeTransaction() {
        // Logic to complete transaction
    }

    public void getTransactionDetails() {
        // Logic to get transaction details
    }

    // Getters and Setters
}
