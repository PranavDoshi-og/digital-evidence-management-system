package com.dems.dao;

import com.dems.model.Evidence;
import com.dems.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvidenceDAO {

    /**
     * Saves evidence metadata and hash to the database.
     * Uses PreparedStatement to prevent SQL Injection.
     */
    public boolean addEvidence(String name, String path, String hash, String desc, int userId) {
        String query = "INSERT INTO evidence (file_name, file_path, file_hash, description, uploaded_by) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, name);
            pstmt.setString(2, path);
            pstmt.setString(3, hash);
            pstmt.setString(4, desc);
            pstmt.setInt(5, userId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
            return false;
        }
    }
}