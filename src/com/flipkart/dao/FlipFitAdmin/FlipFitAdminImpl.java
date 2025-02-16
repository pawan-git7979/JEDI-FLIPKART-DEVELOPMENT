package com.flipkart.dao.FlipFitAdmin;

import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.utils.FlipFitDBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitAdminImpl implements FlipFitAdminInterface {

    @Override
    public List<FlipFitGymOwner> getPendingGymOwnerRequests() {
        List<FlipFitGymOwner> owners = new ArrayList<>();
        try (Connection conn = FlipFitDBUtil.getConnection()) {
            String query = "SELECT * FROM FipFitGymOwner WHERE status = 'PENDING'";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int ownerId = rs.getInt("userId");
                List<String> gymNames = getGymNamesByOwnerId(ownerId, conn); // ✅ Fetch gym names separately

                owners.add(new FlipFitGymOwner(
                        ownerId,
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        "OWNER",
                        rs.getString("address"),
                        gymNames, // ✅ Now correctly passing a List<String>
                        rs.getString("aadhaarNumber"),
                        rs.getString("panNumber"),
                        rs.getString("governmentDocument")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return owners;
    }

    @Override
    public boolean approveGymOwner(int ownerId) {
        try (Connection conn = FlipFitDBUtil.getConnection()) {
            String query = "UPDATE gym_owners SET status = 'APPROVED' WHERE userId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, ownerId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean rejectGymOwner(int ownerId) {
        try (Connection conn = FlipFitDBUtil.getConnection()) {
            String query = "DELETE FROM gym_owners WHERE userId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, ownerId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<String> getAllCustomers() {
        List<String> customers = new ArrayList<>();
        try (Connection conn = FlipFitDBUtil.getConnection()) {
            String query = "SELECT name, email FROM gym_customers";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                customers.add("Name: " + rs.getString("name") + " | Email: " + rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public List<String> getAllOwners() {
        List<String> owners = new ArrayList<>();
        try (Connection conn = FlipFitDBUtil.getConnection()) {
            String query = "SELECT name, email FROM gym_owners WHERE status = 'APPROVED'";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                owners.add("Name: " + rs.getString("name") + " | Email: " + rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return owners;
    }

    // ✅ Fetch gym names for a given ownerId
    private List<String> getGymNamesByOwnerId(int ownerId, Connection conn) {
        List<String> gymNames = new ArrayList<>();
        try {
            String query = "SELECT name FROM gyms WHERE ownerId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, ownerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                gymNames.add(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gymNames;
    }
}
