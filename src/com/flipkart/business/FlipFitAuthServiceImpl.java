package com.flipkart.business;

import com.flipkart.bean.FlipFitUser;
import com.flipkart.dao.FlipFitUser.FlipFitUserInterface;
import com.flipkart.dao.FlipFitUser.FlipFitUserImpl;
import java.util.Scanner;
import com.flipkart.exception.AuthException;
import com.flipkart.utils.FlipFitIOUtils;

public class FlipFitAuthServiceImpl implements FlipFitAuthServiceInterface {
    private FlipFitUserInterface userDAO = new FlipFitUserImpl();

    @Override
    public FlipFitUser login(String email, String password) {
        try {
            FlipFitUser user = userDAO.getUserByEmail(email);

            if (user == null) {
                throw new AuthException("No user found with email: " + email);
            }

            if (!user.getPassword().equals(password)) {
                throw new AuthException("Incorrect password for user with email: " + email);
            }

            return user;
        } catch (AuthException e) {
            System.out.println("Error: " + e.getMessage());
            return null; // Or handle appropriately
        }
    }

    @Override
    public boolean registerUser(Scanner scanner) {
        try {
            System.out.println("=== Register as Gym Customer / Gym Owner ===");
            String name = FlipFitIOUtils.getStringInput("Enter Name: ", scanner);
            String email = FlipFitIOUtils.getStringInput("Enter Email: ", scanner);
            String password = FlipFitIOUtils.getStringInput("Enter Password: ", scanner);
            String role = FlipFitIOUtils.getStringInput("Enter Role (customer/owner): ", scanner);
            String address = FlipFitIOUtils.getStringInput("Enter Address: ", scanner);

            String formattedRole = "";
            if (role.equalsIgnoreCase("customer")) {
                formattedRole = "CUSTOMER";
            } else if (role.equalsIgnoreCase("owner")) {
                formattedRole = "OWNER";
            } else {
                throw new AuthException("Invalid role entered: " + role + ". Please enter 'customer' or 'owner'.");
            }

            FlipFitUser newUser = new FlipFitUser(0, name, email, password, formattedRole, address);

            if (userDAO.addUser(newUser)) {
                System.out.println("Registration successful!");
                return true;
            } else {
                throw new AuthException("Registration failed for user with email: " + email);
            }
        } catch (AuthException e) {
            System.out.println("Error: " + e.getMessage());
            return false; // Or handle appropriately
        }
    }

}

