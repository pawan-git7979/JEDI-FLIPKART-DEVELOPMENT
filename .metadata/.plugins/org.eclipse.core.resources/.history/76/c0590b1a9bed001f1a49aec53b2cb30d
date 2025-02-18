package com.flipkart.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The TestDBConnection class is a utility to test the database connection.
 * It attempts to establish a connection to the database and prints a message based on the result.
 */
public class TestDBConnection {
    /**
     * The main method attempts to connect to the database and prints the result.
     * It uses the FlipFitDBUtil class to obtain the connection and handles any SQLException that may occur.
     *
     * @param args Command-line arguments (not used in this class)
     */
    public static void main(String[] args) {
        System.out.println("Attempting to connect to the database...");

        // Try to establish a connection to the database using FlipFitDBUtil
        try (Connection connection = FlipFitDBUtil.getConnection()) {
            // Check if the connection was successfully established
            if (connection != null) {
                System.out.println("✅ Connection to MySQL database established successfully!");
            } else {
                System.out.println("❌ Failed to establish connection.");
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions that occur during the connection attempt
            System.err.println("Error connecting to database:");
            e.printStackTrace();
        }
    }
}
