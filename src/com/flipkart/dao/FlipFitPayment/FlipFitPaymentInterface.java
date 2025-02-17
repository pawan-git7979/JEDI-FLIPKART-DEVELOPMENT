package com.flipkart.dao.FlipFitPayment;

import com.flipkart.bean.FlipFitPayment;
import com.flipkart.exception.PaymentFailureException;

public interface FlipFitPaymentInterface {
    boolean processPayment(FlipFitPayment payment) throws PaymentFailureException;
    FlipFitPayment getPaymentDetails(int paymentId) throws PaymentFailureException;
    void sendNotification(int userId, int bookingId, int price) throws PaymentFailureException;
}
