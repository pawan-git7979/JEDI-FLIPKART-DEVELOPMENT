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
            String query = """
            SELECT 
                u.userId, u.name, u.email, u.password, u.address, 
                o.aadhaarNumber, o.panNumber, o.governmentDocument 
            FROM FlipFitGymCenter gc
            JOIN FlipFitGymOwner o ON gc.ownerId = o.ownerId
            JOIN FlipFitUser u ON o.ownerId = u.userId
            WHERE gc.status = 'PENDING'
        """;

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int ownerId = rs.getInt("userId");
                List<String> gymNames = getGymNamesByOwnerId(ownerId, conn); // ✅ Fetch gym names separately
                System.out.println("Owner ID: " + rs.getInt("userId"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Aadhaar: " + rs.getString("aadhaarNumber"));
                System.out.println("PAN: " + rs.getString("panNumber"));
                System.out.println("Document: " + rs.getString("governmentDocument"));

                owners.add(new FlipFitGymOwner(
                        ownerId,
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
//                        "OWNER",
                        rs.getString("address"),
                        gymNames, // ✅ Now correctly passing a List<String>
                        rs.getString("aadhaarNumber"),
                        rs.getString("panNumber"),
                        rs.getString("governmentDocument")
                ));
                System.out.println(gymNames);
                System.out.println(owners);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return owners;
    }

    @Override
    public boolean approveGymOwner(int ownerId) {
        try (Connection conn = FlipFitDBUtil.getConnection()) {
            String query = "UPDATE FlipFitGymCenter SET status = 'APPROVED' WHERE ownerId = ?";
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
            // First, delete all gym centers associated with the owner
            String deleteGymsQuery = "DELETE FROM FlipFitGymCenter WHERE ownerId = ?";
            PreparedStatement stmt1 = conn.prepareStatement(deleteGymsQuery);
            stmt1.setInt(1, ownerId);
            stmt1.executeUpdate();

            // Then, delete the owner record
            String deleteOwnerQuery = "DELETE FROM FlipFitGymOwner WHERE ownerId = ?";
            PreparedStatement stmt2 = conn.prepareStatement(deleteOwnerQuery);
            stmt2.setInt(1, ownerId);
            return stmt2.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<String> getAllCustomers() {
        List<String> customers = new ArrayList<>();
        try (Connection conn = FlipFitDBUtil.getConnection()) {
            String query = """
            SELECT u.name, u.email 
            FROM FlipFitGymCustomer c
            JOIN FlipFitUser u ON c.userId = u.userId
            """;
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
            String query = """
            SELECT u.name, u.email 
            FROM FlipFitGymOwner o
            JOIN FlipFitUser u ON o.ownerId = u.userId
            JOIN FlipFitGymCenter gc ON o.ownerId = gc.ownerId
            WHERE gc.status = 'APPROVED'
            """;
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
            String query = "SELECT name FROM FlipFitGymCenter WHERE ownerId = ?";
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
