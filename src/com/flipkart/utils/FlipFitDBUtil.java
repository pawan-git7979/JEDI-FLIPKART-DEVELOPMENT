package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for establishing a connection to the MySQL database.
 * This class contains methods to load the MySQL JDBC driver and get a connection to the database.
 */
public class FlipFitDBUtil {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/FlipFitSchema?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "abcd1234"; // Update with your MySQL password

    // Load the MySQL JDBC Driver when the class is loaded.
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver successfully loaded.");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Make sure the MySQL Connector JAR is added to the classpath.");
            e.printStackTrace();
        }
    }

    /**
     * Returns a connection object to the MySQL database using the credentials specified in the class.
     * This method connects to the FlipFitSchema database using the provided URL, username, and password.
     *
     * @return A Connection object to the MySQL database (FlipFitSchema).
     * @throws SQLException If a database access error occurs, such as invalid credentials or connection issues.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
