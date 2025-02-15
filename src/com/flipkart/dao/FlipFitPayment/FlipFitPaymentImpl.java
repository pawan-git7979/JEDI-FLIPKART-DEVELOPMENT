package com.flipkart.dao.FlipFitPayment;

import com.flipkart.bean.FlipFitPayment;
import com.flipkart.utils.FlipFitDBUtil;
import java.sql.*;

public class FlipFitPaymentImpl implements FlipFitPaymentInterface {
    @Override
    public boolean processPayment(FlipFitPayment payment) {
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO payments (user_id, amount, status) VALUES (?, ?, ?)")) {
            stmt.setInt(1, payment.getUserId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setString(3, payment.getStatus().toString());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public FlipFitPayment getPaymentDetails(int paymentId) {
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM payments WHERE id = ?")) {
            stmt.setInt(1, paymentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                FlipFitPayment payment = new FlipFitPayment();
                payment.setPaymentId(rs.getInt("id"));
                payment.setUserId(rs.getInt("user_id"));
                payment.setAmount(rs.getDouble("amount"));
                return payment;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
