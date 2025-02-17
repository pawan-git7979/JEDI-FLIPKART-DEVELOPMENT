package com.flipkart.dao.FlipFitBooking;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.utils.FlipFitDBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.flipkart.constant.SQLQueries; // ✅ Import query constants

/**
 * Implementation of the FlipFitBookingInterface, responsible for handling the CRUD operations related to gym bookings.
 */
public class FlipFitBookingImpl implements FlipFitBookingInterface {

    /**
     * Retrieves a booking by its unique booking ID.
     *
     * @param bookingId The ID of the booking to retrieve.
     * @return The FlipFitBooking object if found, null if no booking exists with the provided ID.
     */
    @Override
    public FlipFitBooking getBookingById(int bookingId) {
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLQueries.GET_BOOKING_BY_ID)) {
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

    /**
     * Retrieves a list of bookings made by a particular user.
     *
     * @param userId The ID of the user whose bookings are to be retrieved.
     * @return A list of FlipFitBooking objects for the specified user.
     */
    @Override
    public List<FlipFitBooking> getBookingsByUser(int userId) {
        List<FlipFitBooking> bookings = new ArrayList<>();
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLQueries.GET_BOOKINGS_BY_USER)) {
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

    /**
     * Cancels a booking by its unique booking ID.
     *
     * @param bookingId The ID of the booking to cancel.
     * @return true if the booking was successfully canceled, false otherwise.
     */
    @Override
    public boolean cancelBooking(int bookingId) {
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLQueries.CANCEL_BOOKING)) {
            stmt.setInt(1, bookingId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
