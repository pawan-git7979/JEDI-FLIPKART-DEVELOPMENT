package com.flipkart.bean;

/**
 * The FlipFitUser class represents a user in the FlipFit system.
 * It contains essential user details such as user ID, name, email, password, role, and address.
 */
public class FlipFitUser {
    // Constants for user roles
    public static final String ROLE_CUSTOMER = "CUSTOMER"; // Role for customers
    public static final String ROLE_OWNER = "OWNER"; // Role for gym owners
    public static final String ROLE_ADMIN = "ADMIN"; // Role for administrators

    private int userId; // Unique identifier for the user
    private String name; // User's name
    private String email; // User's email address
    private String password; // User's password
    private String role; // Role of the user (CUSTOMER, OWNER, ADMIN)
    private String address; // User's address

    /**
     * Default Constructor.
     * Initializes a new FlipFitUser object without setting any fields.
     */
    public FlipFitUser() {}

    /**
     * Parameterized Constructor.
     * Initializes a new FlipFitUser object with the specified details.
     *
     * @param userId   The unique identifier for the user.
     * @param name     The name of the user.
     * @param email    The email address of the user.
     * @param password The password for the user.
     * @param role     The role of the user (CUSTOMER, OWNER, ADMIN).
     * @param address  The address of the user.
     */
    public FlipFitUser(int userId, String name, String email, String password, String role, String address) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.address = address;
    }

    /**
     * Gets the unique user ID.
     *
     * @return The unique user ID.
     */
    public int getUserId() { return userId; }

    /**
     * Sets the unique user ID.
     *
     * @param userId The unique user ID.
     */
    public void setUserId(int userId) { this.userId = userId; }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() { return name; }

    /**
     * Sets the name of the user.
     *
     * @param name The name of the user.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Gets the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail() { return email; }

    /**
     * Sets the email address of the user.
     *
     * @param email The email address of the user.
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() { return password; }

    /**
     * Sets the password of the user.
     *
     * @param password The password of the user.
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Gets the role of the user (e.g., CUSTOMER, OWNER, ADMIN).
     *
     * @return The role of the user.
     */
    public String getRole() { return role; }

    /**
     * Sets the role of the user (e.g., CUSTOMER, OWNER, ADMIN).
     *
     * @param role The role of the user.
     */
    public void setRole(String role) { this.role = role; }

    /**
     * Gets the address of the user.
     *
     * @return The address of the user.
     */
    public String getAddress() { return address; }

    /**
     * Sets the address of the user.
     *
     * @param address The address of the user.
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * Provides a string representation of the FlipFitUser object.
     *
     * @return A string representing the user's details.
     */
    @Override
    public String toString() {
        return "FlipFitUser { " +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", address='" + address + '\'' +
                " }";
    }
}
