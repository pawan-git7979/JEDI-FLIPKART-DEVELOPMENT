package com.flipkart.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) {
        System.out.println("Attempting to connect to the database...");
        try (Connection connection = FlipFitDBUtil.getConnection()) {
            if (connection != null) {
                System.out.println("✅ Connection to MySQL database established successfully!");
            } else {
                System.out.println("❌ Failed to establish connection.");
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to database:");
            e.printStackTrace();
        }
    }
}
