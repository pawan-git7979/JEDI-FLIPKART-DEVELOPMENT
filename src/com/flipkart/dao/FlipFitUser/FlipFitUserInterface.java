package com.flipkart.dao.FlipFitUser;

import com.flipkart.bean.FlipFitUser;
import java.util.List;

/**
 * Interface defining the operations related to user management in the system.
 * This includes adding, retrieving, updating, and deleting users.
 */
public interface FlipFitUserInterface {

    /**
     * Adds a new user to the system.
     *
     * @param user The FlipFitUser object containing user details.
     * @return true if the user was added successfully, false otherwise.
     */
    boolean addUser(FlipFitUser user);

    /**
     * Retrieves a user by their unique user ID.
     *
     * @param userId The ID of the user to be retrieved.
     * @return The FlipFitUser object corresponding to the user ID, or null if not found.
     */
    FlipFitUser getUserById(int userId);

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email of the user to be retrieved.
     * @return The FlipFitUser object corresponding to the email, or null if not found.
     */
    FlipFitUser getUserByEmail(String email);

    /**
     * Retrieves all users in the system.
     *
     * @return A list of all FlipFitUser objects.
     */
    List<FlipFitUser> getAllUsers();

    /**
     * Updates the details of an existing user in the system.
     *
     * @param user The FlipFitUser object containing the updated user details.
     */
    void updateUser(FlipFitUser user);

    /**
     * Deletes a user by their unique user ID.
     *
     * @param userId The ID of the user to be deleted.
     */
    void deleteUser(int userId);
}
