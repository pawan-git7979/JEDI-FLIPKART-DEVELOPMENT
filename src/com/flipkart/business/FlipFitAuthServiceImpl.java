package com.flipkart.business;

import com.flipkart.bean.FlipFitUser;
import com.flipkart.dao.FlipFitUser.FlipFitUserInterface;
import com.flipkart.dao.FlipFitUser.FlipFitUserImpl;
import java.util.Scanner;
import com.flipkart.exception.AuthException;
import com.flipkart.utils.FlipFitIOUtils;

/**
 * Implementation of the FlipFitAuthServiceInterface that handles user authentication operations such as login and registration.
 */
public class FlipFitAuthServiceImpl implements FlipFitAuthServiceInterface {

    // Interface instance to interact with the data layer (DAO)
    private FlipFitUserInterface userDAO = new FlipFitUserImpl();

    /**
     * Method to handle user login.
     *
     * @param email The email of the user attempting to log in.
     * @param password The password of the user attempting to log in.
     * @return A FlipFitUser object representing the logged-in user if authentication is successful; null otherwise.
     * @throws AuthException If the user cannot be found or the password is incorrect.
     */
    @Override
    public FlipFitUser login(String email, String password) {
        try {
            // Retrieve user by email from the database
            FlipFitUser user = userDAO.getUserByEmail(email);

            // If user is not found, throw an exception
            if (user == null) {
                throw new AuthException("No user found with email: " + email);
            }

            // Check if the entered password matches the stored password
            if (!user.getPassword().equals(password)) {
                throw new AuthException("Incorrect password for user with email: " + email);
            }

            // Return user object if login is successful
            return user;
        } catch (AuthException e) {
            // Handle any authentication exceptions (e.g., invalid email/password)
            System.out.println("Error: " + e.getMessage());
            return null; // Return null in case of failure, or handle appropriately
        }
    }

    /**
     * Method to handle user registration.
     *
     * @param scanner The Scanner object to capture user input from the console.
     * @return true if the registration is successful; false otherwise.
     * @throws AuthException If registration fails due to invalid input or database issues.
     */
    @Override
    public boolean registerUser(Scanner scanner) {
        try {
            // Gather necessary input for user registration
            System.out.println("=== Register as Gym Customer / Gym Owner ===");
            String name = FlipFitIOUtils.getStringInput("Enter Name: ", scanner);  // Get name
            String email = FlipFitIOUtils.getStringInput("Enter Email: ", scanner);  // Get email
            String password = FlipFitIOUtils.getStringInput("Enter Password: ", scanner);  // Get password
            String role = FlipFitIOUtils.getStringInput("Enter Role (customer/owner): ", scanner);  // Get role (customer/owner)
            String address = FlipFitIOUtils.getStringInput("Enter Address: ", scanner);  // Get address

            // Validate role input and format it
            String formattedRole = "";
            if (role.equalsIgnoreCase("customer")) {
                formattedRole = "CUSTOMER";  // Role is customer
            } else if (role.equalsIgnoreCase("owner")) {
                formattedRole = "OWNER";  // Role is owner
            } else {
                // If invalid role entered, throw an exception
                throw new AuthException("Invalid role entered: " + role + ". Please enter 'customer' or 'owner'.");
            }

            // Create a new FlipFitUser object
            FlipFitUser newUser = new FlipFitUser(0, name, email, password, formattedRole, address);

            // Add user to the database and check if the operation was successful
            if (userDAO.addUser(newUser)) {
                System.out.println("Registration successful!");
                return true;  // Return true if registration is successful
            } else {
                // If registration fails, throw an exception
                throw new AuthException("Registration failed for user with email: " + email);
            }
        } catch (AuthException e) {
            // Handle any authentication-related exceptions during registration
            System.out.println("Error: " + e.getMessage());
            return false;  // Return false in case of failure, or handle appropriately
        }
    }
}
