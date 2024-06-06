package com.georgeBall.dao;

import com.georgeBall.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public void save(User user) {
        String sql = "INSERT INTO users(username, password) VALUES(?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
            System.out.println("User saved: " + user.getUsername()); // Debug statement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User find(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String password = rs.getString("password");
                System.out.println("User found: " + username + ", Password: " + password); // Debug statement
                return new User(username, password);
            } else {
                System.out.println("User not found: " + username); // Debug statement
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
