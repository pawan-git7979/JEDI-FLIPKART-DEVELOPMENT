package com.flipkart.dao.FlipFitGymOwner;

import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.utils.FlipFitDBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitGymOwnerImpl implements FlipFitGymOwnerInterface {

    @Override
    public boolean registerGymOwner(FlipFitGymOwner owner) {
        String query = "INSERT INTO FlipFitGymOwner (user_id, gym_names, aadhaar_number, pan_number, government_document) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, owner.getUserId());
            stmt.setString(2, String.join(",", owner.getGymNames())); // Convert list to string
            stmt.setString(3, owner.getAadhaarNumber());
            stmt.setString(4, owner.getPanNumber());
            stmt.setString(5, owner.getGovernmentDocument());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public FlipFitGymOwner getOwnerById(int ownerId) {
        String query = "SELECT * FROM FlipFitGymOwner WHERE user_id = ?";
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ownerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                FlipFitGymOwner owner = new FlipFitGymOwner();
                owner.setUserId(rs.getInt("user_id"));
                owner.setGymNames(List.of(rs.getString("gym_names").split(",")));
                owner.setAadhaarNumber(rs.getString("aadhaar_number"));
                owner.setPanNumber(rs.getString("pan_number"));
                owner.setGovernmentDocument(rs.getString("government_document"));
                return owner;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ New Method: Implement `updateOwnerDetails()`
    @Override
    public boolean updateOwnerDetails(FlipFitGymOwner owner) {
        String query = "UPDATE gym_owners SET gym_names = ?, aadhaar_number = ?, pan_number = ?, government_document = ? WHERE user_id = ?";
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, String.join(",", owner.getGymNames())); // Convert list to string
            stmt.setString(2, owner.getAadhaarNumber());
            stmt.setString(3, owner.getPanNumber());
            stmt.setString(4, owner.getGovernmentDocument());
            stmt.setInt(5, owner.getUserId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<FlipFitGymOwner> getAllOwners() {
        List<FlipFitGymOwner> owners = new ArrayList<>();
        String query = "SELECT * FROM gym_owners";
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FlipFitGymOwner owner = new FlipFitGymOwner();
                owner.setUserId(rs.getInt("user_id"));
                owner.setGymNames(List.of(rs.getString("gym_names").split(",")));
                owner.setAadhaarNumber(rs.getString("aadhaar_number"));
                owner.setPanNumber(rs.getString("pan_number"));
                owner.setGovernmentDocument(rs.getString("government_document"));
                owners.add(owner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return owners;
    }

    @Override
    public boolean addGymCenter(FlipFitGymCenter gym) {
        String query = "INSERT INTO FlipFitGymCenter (name, location, owner_id, admin_id, status) VALUES (?, ?, ?, ?, 'PENDING')";
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, gym.getName());
            stmt.setString(2, gym.getLocation());
            stmt.setInt(3, gym.getOwnerId());
            stmt.setInt(4, gym.getAdminId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateGymInfo(FlipFitGymCenter gym) {
        String query = "UPDATE FlipFitGymCenter SET name = ?, location = ? WHERE id = ?";
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, gym.getName());
            stmt.setString(2, gym.getLocation());
            stmt.setInt(3, gym.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addOrUpdateSlot(int gymId, String startTime, String endTime, int seats) {
        String query = "INSERT INTO gym_slots (gym_id, start_time, end_time, seats) VALUES (?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE start_time = ?, end_time = ?, seats = ?";
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, gymId);
            stmt.setString(2, startTime);
            stmt.setString(3, endTime);
            stmt.setInt(4, seats);
            stmt.setString(5, startTime);
            stmt.setString(6, endTime);
            stmt.setInt(7, seats);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<String> viewBookings(int ownerId) {
        List<String> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE gym_id IN (SELECT id FROM gym_centers WHERE owner_id = ?)";
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ownerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bookings.add("Booking ID: " + rs.getInt("id") + " | Status: " + rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
