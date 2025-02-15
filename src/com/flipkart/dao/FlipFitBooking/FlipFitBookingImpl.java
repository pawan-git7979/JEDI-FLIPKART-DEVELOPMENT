package com.flipkart.dao.FlipFitBooking;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.utils.FlipFitDBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitBookingImpl implements FlipFitBookingInterface {
    @Override
    public FlipFitBooking getBookingById(int bookingId) {
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bookings WHERE id = ?")) {
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                FlipFitBooking booking = new FlipFitBooking();
                booking.setBookingId(rs.getInt("id"));
                booking.setUserId(rs.getInt("user_id"));
                booking.setCenterId(rs.getInt("gym_id"));
                return booking;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<FlipFitBooking> getBookingsByUser(int userId) {
        List<FlipFitBooking> bookings = new ArrayList<>();
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bookings WHERE user_id = ?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                FlipFitBooking booking = new FlipFitBooking();
                booking.setBookingId(rs.getInt("id"));
                booking.setUserId(rs.getInt("user_id"));
                booking.setCenterId(rs.getInt("gym_id"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM bookings WHERE id = ?")) {
            stmt.setInt(1, bookingId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
