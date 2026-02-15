package com.dems.dao;

import com.dems.util.DBConnection;
import java.sql.*;

public class EvidenceDAO {
    public int addEvidence(String name, String path, String hash, String desc, int userId) {
        String query = "INSERT INTO evidence (file_name, file_path, file_hash, description, uploaded_by) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, name);
            pstmt.setString(2, path);
            pstmt.setString(3, hash);
            pstmt.setString(4, desc);
            pstmt.setInt(5, userId);

            if (pstmt.executeUpdate() > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }
}