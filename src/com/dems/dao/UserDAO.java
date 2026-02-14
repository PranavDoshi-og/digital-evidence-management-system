package com.dems.dao;

import com.dems.model.User;
import com.dems.util.DBConnection;
import com.dems.util.HashUtil;
import java.sql.*;

public class UserDAO {

    /**
     * REGISTRATION: Saves a new user to the MySQL 'users' table.
     * Uses PreparedStatement to prevent SQL Injection.
     */
    public boolean registerUser(String username, String password, String role) {
        String query = "INSERT INTO users (username, password_hash, role) VALUES (?, ?, ?)";
        
        // Hash the password before saving it for security
        String hashedPassword = HashUtil.generateHash(password);

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            pstmt.setString(3, role.toUpperCase());

            int result = pstmt.executeUpdate();
            return result > 0; // Returns true if a row was inserted
        } catch (SQLException e) {
            System.err.println("Registration Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * LOGIN: Checks if the username and hashed password match a record in the DB.
     * Returns a User object if successful.
     */
    public User login(String username, String password) {
        String query = "SELECT user_id, username, role FROM users WHERE username = ? AND password_hash = ?";
        String hashedPassword = HashUtil.generateHash(password);

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Return a User object to keep track of who is logged in
                return new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            System.err.println("Login Error: " + e.getMessage());
        }
        return null; // Login failed
    }
}