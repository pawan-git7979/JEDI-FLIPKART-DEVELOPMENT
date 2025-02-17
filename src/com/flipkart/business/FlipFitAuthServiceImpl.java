package com.flipkart.business;

import com.flipkart.bean.FlipFitUser;
import com.flipkart.dao.FlipFitUser.FlipFitUserInterface;
import com.flipkart.dao.FlipFitUser.FlipFitUserImpl;
import java.util.Scanner;
import com.flipkart.utils.FlipFitIOUtils;

public class FlipFitAuthServiceImpl implements FlipFitAuthServiceInterface {
    private FlipFitUserInterface userDAO = new FlipFitUserImpl();

    @Override
    public FlipFitUser login(String email, String password) {
        FlipFitUser user = userDAO.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public boolean registerUser(Scanner scanner) {
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
            System.out.println("Invalid role. Please enter 'customer' or 'owner'.");
            return false;
        }

        FlipFitUser newUser = new FlipFitUser(0, name, email, password, formattedRole, address);

        if (userDAO.addUser(newUser)) {
            System.out.println("Registration successful!");
            return true;
        } else {
            System.out.println("Registration failed.");
            return false;
        }
    }
}
