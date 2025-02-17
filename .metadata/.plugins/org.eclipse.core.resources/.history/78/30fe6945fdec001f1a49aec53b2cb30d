package com.flipkart.dao.FlipFitAdmin;

import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.utils.FlipFitDBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.flipkart.constant.SQLQueries; // ✅ Import query constants

public class FlipFitAdminImpl implements FlipFitAdminInterface {

    @Override
    public List<FlipFitGymOwner> getPendingGymOwnerRequests() {
        List<FlipFitGymOwner> owners = new ArrayList<>();
        try (Connection conn = FlipFitDBUtil.getConnection()) {
            String query = SQLQueries.GET_PENDING_GYM_OWNER_REQUESTS;

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int ownerId = rs.getInt("userId");
                List<String> gymNames = getGymNamesByOwnerId(ownerId, conn);

                owners.add(new FlipFitGymOwner(
                        ownerId,
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("address"),
                        gymNames,
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
            String query = SQLQueries.APPROVE_GYM_OWNER;
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
            String deleteGymsQuery = SQLQueries.DELETE_GYMS_BY_OWNER;
            PreparedStatement stmt1 = conn.prepareStatement(deleteGymsQuery);
            stmt1.setInt(1, ownerId);
            stmt1.executeUpdate();

            String deleteOwnerQuery = SQLQueries.DELETE_GYM_OWNER;
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
        String query = SQLQueries.GET_ALL_CUSTOMERS;

        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                System.out.println("No customers found.");
            }

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
        String query = SQLQueries.GET_ALL_OWNERS;

        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                System.out.println("No approved owners found.");
            }

            while (rs.next()) {
                owners.add("Name: " + rs.getString("name") + " | Email: " + rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return owners;
    }

    private List<String> getGymNamesByOwnerId(int ownerId, Connection conn) {
        List<String> gymNames = new ArrayList<>();

        if (conn == null) {
            System.out.println("Database connection is null. Check FlipFitDBUtil.getConnection()");
            return gymNames;
        }

        String query = SQLQueries.GET_GYM_NAMES_BY_OWNER;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ownerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    System.out.println("No gyms found for ownerId: " + ownerId);
                }

                while (rs.next()) {
                    gymNames.add(rs.getString("name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gymNames;
    }

    @Override
    public List<String> getAllGyms() {
        List<String> gyms = new ArrayList<>();
        String query = SQLQueries.GET_ALL_GYMS;

        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                System.out.println("No gyms found.");
            }

            while (rs.next()) {
                gyms.add("Name: " + rs.getString("name") + " | Location: " + rs.getString("location"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gyms;
    }
}
