package com.flipkart.dao.FlipFitCustomer;

import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitGymSlot;
import com.flipkart.bean.FlipFitBooking;
import com.flipkart.utils.FlipFitDBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitCustomerImpl implements FlipFitCustomerInterface {

    @Override
    public List<String> getAvailableCities() {
        List<String> cities = new ArrayList<>();
        String query = "SELECT DISTINCT location FROM gyms";
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cities.add(rs.getString("location"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public List<FlipFitGymCenter> getGymsByCity(String city) {
        List<FlipFitGymCenter> gyms = new ArrayList<>();
        String query = "SELECT * FROM gyms WHERE location = ?";
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                gyms.add(new FlipFitGymCenter(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getInt("adminId"),
                        rs.getInt("ownerId"),
                        rs.getString("ownerName"),
                        null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gyms;
    }

    @Override
    public List<FlipFitGymSlot> getAvailableSlots(int gymId) {
        List<FlipFitGymSlot> slots = new ArrayList<>();
        String query = "SELECT * FROM slots WHERE gymId = ? AND numOfSeats > numOfSeatsBooked";
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, gymId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                slots.add(new FlipFitGymSlot(
                        rs.getInt("slotId"),
                        rs.getInt("gymId"),
                        rs.getString("startTime"),
                        rs.getString("endTime"),
                        rs.getString("trainer"),
                        rs.getInt("numOfSeats"),
                        rs.getInt("numOfSeatsBooked"),
                        null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slots;
    }

    @Override
    public void addToWaitlist(int userId, int slotId) {
        String query = "INSERT INTO waitlist (userId, slotId) VALUES (?, ?)";
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, slotId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FlipFitBooking bookSlot(int userId, int gymId, int slotId) {
        String query = "INSERT INTO bookings (userId, gymId, slotId, status) VALUES (?, ?, ?, 'BOOKED')";
        String updateSlotQuery = "UPDATE slots SET numOfSeatsBooked = numOfSeatsBooked + 1 WHERE slotId = ?";
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement updateSlotStmt = conn.prepareStatement(updateSlotQuery)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, gymId);
            stmt.setInt(3, slotId);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                updateSlotStmt.setInt(1, slotId);
                updateSlotStmt.executeUpdate();

                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return new FlipFitBooking(
                            generatedKeys.getInt(1),
                            userId,
                            gymId,
                            slotId,
                            "BOOKED",
                            0,
                            null,
                            null
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<FlipFitBooking> getUserBookings(int userId) {
        List<FlipFitBooking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE userId = ?";
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bookings.add(new FlipFitBooking(
                        rs.getInt("bookingId"),
                        rs.getInt("userId"),
                        rs.getInt("gymId"),
                        rs.getInt("slotId"),
                        rs.getString("status"),
                        rs.getInt("paymentId"),
                        rs.getTimestamp("bookingTime"),
                        rs.getTimestamp("updatedTime")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
