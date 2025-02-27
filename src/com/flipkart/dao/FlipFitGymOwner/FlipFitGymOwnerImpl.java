package com.flipkart.dao.FlipFitGymOwner;

import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.constant.SQLQueries;
import com.flipkart.utils.FlipFitDBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlipFitGymOwnerImpl implements FlipFitGymOwnerInterface {

    /**
     * Registers a new gym owner in the system.
     *
     * @param owner The FlipFitGymOwner object containing the details of the owner to be registered.
     * @return true if the owner is successfully registered, false otherwise.
     */
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

    /**
     * Retrieves the details of a gym owner by their ownerId.
     *
     * @param ownerId The unique identifier of the gym owner.
     * @return The FlipFitGymOwner object containing the owner's details, or null if not found.
     */
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

    /**
     * Updates the details of an existing gym owner.
     *
     * @param owner The FlipFitGymOwner object containing the updated details of the owner.
     * @return true if the update is successful, false otherwise.
     */
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

    /**
     * Retrieves a list of all gym owners from the system.
     *
     * @return A list of FlipFitGymOwner objects representing all the gym owners.
     */
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

    /**
     * Adds a new gym center to the system.
     *
     * @param gym The FlipFitGymCenter object containing the details of the gym to be added.
     * @return true if the gym is successfully added, false otherwise.
     */
    @Override
    public boolean addGymCenter(FlipFitGymCenter gym) {
        String query = SQLQueries.ADD_GYM_CENTER;
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

    /**
     * Updates the details of an existing gym center.
     *
     * @param gym The FlipFitGymCenter object containing the updated details of the gym.
     * @return true if the update is successful, false otherwise.
     */
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

    /**
     * Adds or updates a gym slot in the system.
     *
     * @param gymId The ID of the gym where the slot is to be added or updated.
     * @param startTime The start time of the gym slot.
     * @param endTime The end time of the gym slot.
     * @param seats The number of available seats in the slot.
     * @param price The price for the gym slot.
     * @return true if the slot is successfully added or updated, false otherwise.
     */
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
    public List<String> getAllGymsByOwner(int ownerId) {
        List<String> gyms = new ArrayList<>();

        // Directly obtain the connection from FlipFitDBUtil
        try (Connection conne = FlipFitDBUtil.getConnection()) {

            if (conne == null) {
                System.out.println("Database connection is null. Check FlipFitDBUtil.getConnection()");
                return gyms;
            }

            // SQL query to get gyms by ownerId
            String query = SQLQueries.GET_GYM_ID_BY_OWNER;

            try (PreparedStatement stmt = conne.prepareStatement(query)) {
                stmt.setInt(1, ownerId); // Set ownerId as a parameter in the query
                ResultSet rs = stmt.executeQuery();

                if (!rs.isBeforeFirst()) {
                    System.out.println("No gyms found.");
                }

                // Collect gym information
                while (rs.next()) {
                    gyms.add("ID: " + rs.getInt("gymId") + " | Name: " + rs.getString("name") + " | Location: " + rs.getString("location"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Sort gyms alphabetically by gym name
        return gyms.stream()
                   .sorted() // Sort by gym name
                   .collect(Collectors.toList());
    }

    /**
     * Views the bookings for a specific gym owner.
     *
     * @param ownerId The ID of the gym owner whose bookings are to be viewed.
     * @return A list of booking details in string format (e.g., "Booking ID: X | Status: Y").
     */
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
