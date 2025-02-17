package com.flipkart.dao.FlipFitPayment;

import com.flipkart.bean.FlipFitPayment;
import com.flipkart.constant.SQLQueries;
import com.flipkart.utils.FlipFitDBUtil;
import java.sql.*;

/**
 * Implementation of the FlipFitPaymentInterface that handles payment processing and payment details retrieval.
 */
public class FlipFitPaymentImpl implements FlipFitPaymentInterface {

    /**
     * Processes the payment for a user.
     *
     * @param payment The FlipFitPayment object containing the payment details to be processed.
     * @return true if the payment is processed successfully, false otherwise.
     */
    @Override
    public boolean processPayment(FlipFitPayment payment) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves the payment details for a specific payment ID.
     *
     * @param paymentId The ID of the payment whose details need to be fetched.
     * @return A FlipFitPayment object containing the payment details, or null if no payment is found with the given ID.
     */
    @Override
    public FlipFitPayment getPaymentDetails(int paymentId) {
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
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sends a notification to a user regarding their booking and payment details.
     *
     * @param userId The ID of the user to be notified.
     * @param bookingId The ID of the booking related to the payment.
     * @param price The total price of the booking.
     */
    public void sendNotification(int userId, int bookingId, int price) {
        String message = "Your booking with ID " + bookingId + " has been processed. The total price is $" + price + ".";
        String query = SQLQueries.NOTIFY_USER;

        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, message);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
