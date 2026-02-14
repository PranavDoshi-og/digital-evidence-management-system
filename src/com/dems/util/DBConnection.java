package com.dems.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class to manage MySQL database connections for the DEMS project.
 * Follows professional JDBC practices by using a Singleton-like approach.
 */
public class DBConnection {
    // 1. Database URL: 'dems' is the name of the database we will create in MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/dems";
    
    // 2. Database User: Default is usually 'root'
    private static final String USER = "root"; 
    
    // 3. Database Password: Use the password you set during MySQL installation
    private static final String PASSWORD = "your_password"; 

    private static Connection connection = null;

    /**
     * Establishes and returns a connection to the MySQL database.
     * @return Connection object or null if connection fails.
     */
    public static Connection getConnection() {
        try {
            // Ensure the connection is only created if it doesn't exist or is closed
            if (connection == null || connection.isClosed()) {
                // Explicitly load the MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Create the connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Database connected successfully!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL JDBC Driver not found. Add the JAR to Referenced Libraries.");
        } catch (SQLException e) {
            System.err.println("❌ Connection failed: " + e.getMessage());
        }
        return connection;
    }

    /**
     * Safely closes the database connection.
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔌 Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}