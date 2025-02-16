package com.flipkart.dao.FlipFitPayment;

import com.flipkart.bean.FlipFitPayment;
import com.flipkart.constant.SQLQueries;
import com.flipkart.utils.FlipFitDBUtil;
import java.sql.*;

public class FlipFitPaymentImpl implements FlipFitPaymentInterface {

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
}
