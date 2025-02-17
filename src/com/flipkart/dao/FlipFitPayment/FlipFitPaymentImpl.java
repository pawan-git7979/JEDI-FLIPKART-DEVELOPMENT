package com.flipkart.dao.FlipFitPayment;

import com.flipkart.bean.FlipFitPayment;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.PaymentFailureException;
import com.flipkart.utils.FlipFitDBUtil;
import java.sql.*;

public class FlipFitPaymentImpl implements FlipFitPaymentInterface {

    @Override
    public boolean processPayment(FlipFitPayment payment) throws PaymentFailureException {
        String query = SQLQueries.PROCESS_PAYMENT;
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, payment.getUserId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setString(3, payment.getStatus());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    payment.setPaymentId(generatedKeys.getInt(1));
                }
                return true;
            } else {
                throw new PaymentFailureException("Payment could not be processed.");
            }
        } catch (SQLException e) {
            throw new PaymentFailureException("Database error during payment processing: " + e.getMessage());
        }
    }

    @Override
    public FlipFitPayment getPaymentDetails(int paymentId) throws PaymentFailureException {
        String query = SQLQueries.GET_PAYMENT_DETAILS;
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, paymentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                FlipFitPayment payment = new FlipFitPayment();
                payment.setPaymentId(rs.getInt("id"));
                payment.setUserId(rs.getInt("user_id"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setStatus(rs.getString("status"));
                return payment;
            }
        } catch (SQLException e) {
            throw new PaymentFailureException("Failed to fetch payment details: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void sendNotification(int userId, int bookingId, int price) throws PaymentFailureException {
        String message = "Your booking with ID " + bookingId + " has been processed. The total price is $" + price + ".";
        String query = SQLQueries.NOTIFY_USER;

        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, message);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new PaymentFailureException("Failed to send payment notification.");
            }
        } catch (SQLException e) {
            throw new PaymentFailureException("Database error while sending notification: " + e.getMessage());
        }
    }
}
