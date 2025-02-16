package com.flipkart.dao.FlipFitUser;
import java.util.Scanner;
import java.util.UUID;

import com.flipkart.bean.FlipFitGymCustomer;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitUser;
import com.flipkart.constant.SQLQueries;
import com.flipkart.dao.FlipFitCustomer.FlipFitCustomerImpl;
import com.flipkart.dao.FlipFitGymOwner.FlipFitGymOwnerImpl;
import com.flipkart.utils.FlipFitDBUtil;
import com.flipkart.utils.FlipFitIOUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitUserImpl implements FlipFitUserInterface {
    private Scanner scanner = new Scanner(System.in); // Define scanner once

    public boolean addUser(FlipFitUser user) {
        try (Connection conn = FlipFitDBUtil.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            // Insert into FlipFitUser table
            String userInsertQuery = SQLQueries.ADD_USER;
            try (PreparedStatement userStmt = conn.prepareStatement(userInsertQuery, Statement.RETURN_GENERATED_KEYS)) {

                userStmt.setString(1, user.getName());
                userStmt.setString(2, user.getEmail());
                userStmt.setString(3, user.getPassword());
                userStmt.setString(4, user.getRole());
                userStmt.setString(5, user.getAddress());

                int affectedRows = userStmt.executeUpdate();
                if (affectedRows == 0) {
                    System.out.println("❌ ERROR: User insertion failed!");
                    return false;
                }

                ResultSet generatedKeys = userStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedUserId = generatedKeys.getInt(1);
                    user.setUserId(generatedUserId);
                    System.out.println("✅ User inserted successfully with userId: " + generatedUserId);
                } else {
                    System.out.println("❌ ERROR: No generated key found for user!");
                    return false;
                }
            }

            // Commit user insertion immediately
            conn.commit();
            System.out.println("📌 User record committed successfully!");

            boolean roleInsertSuccess = false;

            // Insert into respective role-specific table
            if ("CUSTOMER".equals(user.getRole())) {
                FlipFitCustomerImpl customerDAO = new FlipFitCustomerImpl();
                FlipFitGymCustomer customer = new FlipFitGymCustomer();
                customer.setUserId(user.getUserId());

                System.out.println("=== Please Enter some important Gym Customer Details ===");
                String uInput = FlipFitIOUtils.getStringInput("Enter Government Document Number: ", new Scanner(System.in));
                System.out.println("📌 Received Government Document Number: " + uInput);
                customer.setGovernmentDocumentNumber(uInput);

                System.out.println("📌 Registering customer with userId: " + customer.getUserId());
                roleInsertSuccess = customerDAO.registerGymCustomer(customer);

            } else if ("OWNER".equals(user.getRole())) {
                FlipFitGymOwnerImpl ownerDAO = new FlipFitGymOwnerImpl();
                FlipFitGymOwner owner = new FlipFitGymOwner();
                owner.setUserId(user.getUserId());

                System.out.print("Enter Aadhaar Number: ");
                String aadhaar = new Scanner(System.in).nextLine();
                owner.setAadhaarNumber(aadhaar);

                System.out.print("Enter PAN Number: ");
                String pan = new Scanner(System.in).nextLine();
                owner.setPanNumber(pan);

                System.out.print("Enter Government Document: ");
                String document = new Scanner(System.in).nextLine();
                owner.setGovernmentDocument(document);

                System.out.println("📌 Registering gym owner with userId: " + owner.getUserId());
                roleInsertSuccess = ownerDAO.registerGymOwner(owner);
            }

            if (!roleInsertSuccess) {
                System.out.println("⚠️ WARNING: Role-specific insertion failed! User is still registered in the system.");
            } else {
                System.out.println("✅ User and Role registered successfully!");
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
public FlipFitUser getUserById(int userId) {
    FlipFitUser user = null;
    try (Connection conn = FlipFitDBUtil.getConnection()) {
        String query = SQLQueries.GET_USER_BY_ID;
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            user = new FlipFitUser(
                    rs.getInt("userId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getString("address")
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return user;
}

@Override
public FlipFitUser getUserByEmail(String email) {
    FlipFitUser user = null;
    try (Connection conn = FlipFitDBUtil.getConnection()) {
        String query =SQLQueries.GET_USER_BY_EMAIL;
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            user = new FlipFitUser(
                    rs.getInt("userId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getString("address")
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return user;
}

@Override
public List<FlipFitUser> getAllUsers() {
    List<FlipFitUser> users = new ArrayList<>();
    try (Connection conn = FlipFitDBUtil.getConnection()) {
            String query = SQLQueries.GET_ALL_USERS;
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            users.add(new FlipFitUser(
                    rs.getInt("userId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getString("address")
            ));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return users;
}

@Override
public void updateUser(FlipFitUser user) {
    try (Connection conn = FlipFitDBUtil.getConnection()) {
        String query = SQLQueries.UPDATE_USER;
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, user.getPassword());
        stmt.setString(4, user.getRole());
        stmt.setString(5, user.getAddress());
        stmt.setInt(6, user.getUserId());
        stmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

@Override
public void deleteUser(int userId) {
    try (Connection conn = FlipFitDBUtil.getConnection()) {
        String query = SQLQueries.DELETE_USER;
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}