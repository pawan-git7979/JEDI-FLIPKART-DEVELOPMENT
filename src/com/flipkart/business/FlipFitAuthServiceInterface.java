package com.flipkart.business;

import com.flipkart.bean.FlipFitUser;
import com.flipkart.exception.AuthException;

import java.util.Scanner;

/**
 * Interface representing the authentication services provided for users (Gym customers and owners).
 * Includes methods for user login and registration.
 */
public interface FlipFitAuthServiceInterface {

    /**
     * Method for authenticating a user based on their email and password.
     *
     * @param email The email address of the user attempting to log in.
     * @param password The password provided by the user for login.
     * @return The authenticated FlipFitUser object if the credentials are correct; null if authentication fails.
     * @throws AuthException If authentication fails due to incorrect credentials or other errors.
     */
    FlipFitUser login(String email, String password);

    /**
     * Method for registering a new user (either Gym customer or Gym owner).
     *
     * @param scanner The Scanner object used to capture user input.
     * @return true if the registration is successful; false if it fails.
     * @throws AuthException If an error occurs during registration, such as invalid inputs.
     */
    boolean registerUser(Scanner scanner);
}
