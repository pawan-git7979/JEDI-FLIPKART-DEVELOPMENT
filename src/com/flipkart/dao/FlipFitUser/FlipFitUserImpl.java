package com.flipkart.dao.FlipFitUser;
import java.util.Scanner;
import java.util.UUID;

import com.flipkart.bean.FlipFitGymCustomer;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitUser;
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

            String userInsertQuery = "INSERT INTO FlipFitUser (name, email, password, role, address) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement userStmt = conn.prepareStatement(userInsertQuery, Statement.RETURN_GENERATED_KEYS)) {

                userStmt.setString(1, user.getName());
                userStmt.setString(2, user.getEmail());
                userStmt.setString(3, user.getPassword());
                userStmt.setString(4, user.getRole());
                userStmt.setString(5, user.getAddress());

                int affectedRows = userStmt.executeUpdate();
                if (affectedRows == 0) {
                    conn.rollback();  // Rollback on user insert failure
                    return false;
                }

                ResultSet generatedKeys = userStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getInt(1));
                } else {
                    conn.rollback(); // Rollback if no generated key
                    return false;
                }
            }

            boolean roleInsertSuccess = false;

            if ("CUSTOMER".equals(user.getRole())) {
                FlipFitCustomerImpl customerDAO = new FlipFitCustomerImpl();
                FlipFitGymCustomer customer = new FlipFitGymCustomer();
                customer.setUserId(user.getUserId());
                System.out.println("=== Please Enter some important Gym Customer Details");
                String uInput=FlipFitIOUtils.getStringInput("Enter Government Document Number: ", new Scanner(System.in));
                System.out.println(uInput);
                customer.setGovernmentDocumentNumber(uInput);
                System.out.println("done till settings");
                roleInsertSuccess = customerDAO.registerGymCustomer(customer);
                System.out.println("Done till success flag");
            } else if ("OWNER".equals(user.getRole())) {
                FlipFitGymOwnerImpl ownerDAO = new FlipFitGymOwnerImpl();
                FlipFitGymOwner owner = new FlipFitGymOwner();
                owner.setUserId(user.getUserId());


                //owner.setAadhaarNumber(FlipFitIOUtils.getStringInput("Enter Aadhaar Number: ", scanner));
                //owner.setPanNumber(FlipFitIOUtils.getStringInput("Enter PAN Number: ", scanner));
                //owner.setGovernmentDocument(FlipFitIOUtils.getStringInput("Enter Government Document: ", scanner));

                System.out.print("Enter Aadhaar Number: ");
                String aadhaar = scanner.nextLine(); // Use nextLine() consistently
                owner.setAadhaarNumber(aadhaar);

                System.out.print("Enter PAN Number: ");
                String pan = scanner.nextLine();    // Use nextLine() consistently
                owner.setPanNumber(pan);

                System.out.print("Enter Government Document: ");
                String document = scanner.next(); // Use nextLine() consistently
                owner.setGovernmentDocument(document);


                roleInsertSuccess = ownerDAO.registerGymOwner(owner);
            }

            if (!roleInsertSuccess) {
                conn.rollback();
                return false;
            }

            conn.commit(); // Commit transaction if everything is successful
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
        String query = "SELECT * FROM FlipFitUser WHERE userId = ?";
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
        String query = "SELECT * FROM FlipFitUser WHERE email = ?";
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
        String query = "SELECT * FROM FlipFitUser";
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
        String query = "UPDATE FlipFitUser SET name = ?, email = ?, password = ?, role = ?, address = ? WHERE userId = ?";
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
        String query = "DELETE FROM FlipFitUser WHERE userId = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}