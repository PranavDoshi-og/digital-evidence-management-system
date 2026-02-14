package com.dems.model;

/**
 * Model class representing a User in the DEMS system.
 * This class acts as a Data Transfer Object (DTO).
 */
public class User {
    private int userId;
    private String username;
    private String role;

    public User(int userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    // Standard Getters to access private data safely
    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
}