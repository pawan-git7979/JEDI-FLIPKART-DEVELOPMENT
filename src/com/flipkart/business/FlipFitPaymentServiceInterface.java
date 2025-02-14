package com.flipkart.business;

import com.flipkart.bean.FlipFitUser;

public interface FlipFitPaymentServiceInterface {
    boolean processPayment(FlipFitUser user, double amount);
}
