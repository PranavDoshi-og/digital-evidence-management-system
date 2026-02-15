package com.dems.dao;

import com.dems.util.DBConnection;
import java.sql.*;

public class AuditDAO {
    public void logAction(int evidenceId, int userId, String action) {
        String query = "INSERT INTO audit_logs (evidence_id, user_id, action_performed) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, evidenceId);
            pstmt.setInt(2, userId);
            pstmt.setString(3, action);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}