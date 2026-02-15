package com.dems.dao;

import com.dems.util.DBConnection;
import java.sql.*;

public class EvidenceDAO {

    /**
     * Saves evidence metadata and returns the generated Evidence ID.
     * Returns -1 if the insertion fails.
     */
    public int addEvidence(String name, String path, String hash, String desc, int userId) {
        String query = "INSERT INTO evidence (file_name, file_path, file_hash, description, uploaded_by) VALUES (?, ?, ?, ?, ?)";

        // We ask MySQL to return the auto-incremented ID
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, name);
            pstmt.setString(2, path);
            pstmt.setString(3, hash);
            pstmt.setString(4, desc);
            pstmt.setInt(5, userId);

            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                // Retrieve the generated ID
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
        }
        return -1;
    }
}