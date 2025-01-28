package com.tap.model;

import java.sql.Timestamp;

public class User {

    private int userId;              // Primary Key
    private String name;
    private String email;
    private String phoneNumber;      // Changed to String
    private String address;
    private String userName;
    private String password;
    private String role;              // ENUM 'customer', 'restaurantOwner', 'deliveryBoy', 'systemAdmin'
    private Timestamp createdDate;    // Auto-generated
    private Timestamp lastLogin;      // Auto-updated

    // Zero-parameter constructor
    public User() {
    }

    // Constructor with all parameters (including auto-generated fields)
    public User(int userId, String name, String email, String phoneNumber, String address, String userName, String password, String role, Timestamp createdDate, Timestamp lastLogin) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.createdDate = createdDate;
        this.lastLogin = lastLogin;
    }

    // Constructor with all parameters except id (auto-generated fields will be handled in database)
    public User(String name, String email, String phoneNumber, String address, String userName, String password, String role) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    // toString method
    @Override
    public String toString() {
        return userId + " " + name + " " + email + " " + phoneNumber + " " + address + " " + userName + " " + role + " " + createdDate + " " + lastLogin;
    }
}
