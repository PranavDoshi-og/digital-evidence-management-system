package com.dems.dao;

import com.dems.model.User;
import com.dems.util.DBConnection;
import com.dems.util.HashUtil;
import java.sql.*;

public class UserDAO {
    public boolean registerUser(String username, String password, String role) {
        String sql = "INSERT INTO users (username, password_hash, role) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, HashUtil.generateHash(password));
            pstmt.setString(3, role);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, HashUtil.generateHash(password));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("role"));
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}