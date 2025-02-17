package com.flipkart.dao.FlipFitBooking;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.utils.FlipFitDBUtil;
import com.flipkart.exception.BookingNotFoundException;
import com.flipkart.constant.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitBookingImpl implements FlipFitBookingInterface {

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
            } else {
                throw new BookingNotFoundException("Booking with ID " + bookingId + " not found.");
            }

        } catch (BookingNotFoundException e) {
            System.err.println("Error: " + e.getMessage()); // Custom Exception Handling
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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

            if (bookings.isEmpty()) {
                throw new BookingNotFoundException("No bookings found for user ID " + userId);
            }

        } catch (BookingNotFoundException e) {
            System.err.println("Error: " + e.getMessage()); // Custom Exception Handling
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLQueries.CANCEL_BOOKING)) {

            stmt.setInt(1, bookingId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new BookingNotFoundException("Cannot cancel: Booking ID " + bookingId + " does not exist.");
            }

            return true;
        } catch (BookingNotFoundException e) {
            System.err.println("Error: " + e.getMessage()); // Custom Exception Handling
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
