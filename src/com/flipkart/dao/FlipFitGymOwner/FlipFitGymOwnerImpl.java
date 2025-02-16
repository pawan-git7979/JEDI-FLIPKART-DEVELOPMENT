package com.flipkart.dao.FlipFitGymOwner;

import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.constant.SQLQueries;
import com.flipkart.utils.FlipFitDBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitGymOwnerImpl implements FlipFitGymOwnerInterface {


    public boolean registerGymOwner(FlipFitGymOwner owner) {
        try (Connection conn = FlipFitDBUtil.getConnection()) {
            String insertQuery = SQLQueries.REGISTER_GYM_OWNER; // ownerId is the foreign key referencing userId
            try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                stmt.setInt(1, owner.getUserId()); // Use userId here!
                stmt.setString(2, owner.getAadhaarNumber());
                stmt.setString(3, owner.getPanNumber());
                stmt.setString(4, owner.getGovernmentDocument());

                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public FlipFitGymOwner getOwnerById(int ownerId) {
        String query = SQLQueries.GET_OWNER_BY_ID;
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
        String query = SQLQueries.UPDATE_OWNER_DETAILS;
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
        String query = SQLQueries.GET_ALL_OWNERS;
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
        String query = SQLQueries.ADD_GYM_CENTER;
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, gym.getName());
            stmt.setString(2, gym.getLocation());
            stmt.setInt(3, gym.getOwnerId());
            stmt.setInt(4, gym.getAdminId());
//            System.out.println(stmt);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean updateGymInfo(FlipFitGymCenter gym) {
        String query = SQLQueries.UPDATE_GYM_DETAILS;
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
    public boolean addOrUpdateSlot(int gymId, String startTime, String endTime, int seats, int price) {
        String query = SQLQueries.ADD_OR_UPDATE_SLOT;
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, gymId);
            stmt.setString(2, startTime);
            stmt.setString(3, endTime);
            stmt.setInt(4, seats);
            stmt.setString(5, startTime);
            stmt.setString(6, endTime);
            stmt.setInt(7, seats);
            stmt.setInt(8, price);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<String> viewBookings(int ownerId) {
        List<String> bookings = new ArrayList<>();
        String query = SQLQueries.VIEW_BOOKINGS;
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ownerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bookings.add("Booking ID: " + rs.getInt("bookingId") + " | Status: " + rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
