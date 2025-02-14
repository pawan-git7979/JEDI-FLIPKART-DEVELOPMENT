package com.flipkart.business;

import com.flipkart.bean.FlipFitUser;

public class FlipFitPaymentService implements FlipFitPaymentServiceInterface {

    @Override
    public boolean processPayment(FlipFitUser user, double amount) {
        System.out.println("Processing payment of $" + amount + " for " + user.getName() + "...");
        // Simulate payment success
        System.out.println("Payment successful!");
        return true;
    }
}
