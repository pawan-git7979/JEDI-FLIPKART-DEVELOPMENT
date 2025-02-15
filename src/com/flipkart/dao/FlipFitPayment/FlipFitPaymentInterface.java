package com.flipkart.dao.FlipFitPayment;

import com.flipkart.bean.FlipFitPayment;

public interface FlipFitPaymentInterface {
    boolean processPayment(FlipFitPayment payment);
    FlipFitPayment getPaymentDetails(int paymentId);
}
