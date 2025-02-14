package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FlipFitDBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/FlipFitSchema?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "new changed"; // Update with your MySQL password

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
     * Returns a Connection object to the MySQL database.
     *
     * @return Connection to FlipFitSchema
     * @throws SQLException if a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
