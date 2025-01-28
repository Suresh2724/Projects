package com.tap.model;

import java.sql.Timestamp;

public class Orders {

    private int orderId;              // Primary Key
    private int restaurantId;         // Foreign Key referencing Restaurant(restaurantId)
    private int userId;               // Foreign Key referencing User(userId)
    private int totalAmount;          // Total amount for the order
    private String modeOfPayment;     // Payment mode (upi, cashOnDelivery, creditCard, debitCard)
    private String status;            // Order status (confirm, dispatch, delivered)
    private Timestamp orderDate;      // Order date (from CURRENT_TIMESTAMP)

    // Zero-parameter constructor
    public Orders() {
    }

    // Constructor with all parameters
    public Orders(int orderId, int restaurantId, int userId, int totalAmount, String modeOfPayment, String status, Timestamp orderDate) {
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.modeOfPayment = modeOfPayment;
        this.status = status;
        this.orderDate = orderDate;
    }

    // Constructor excluding auto-generated id and orderDate
    public Orders(int restaurantId, int userId, int totalAmount, String modeOfPayment, String status) {
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.modeOfPayment = modeOfPayment;
        this.status = status;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    // toString method
    @Override
    public String toString() {
        return orderId + " " + restaurantId + " " + userId + " " + totalAmount + " " + modeOfPayment + " " + status + " " + orderDate;
    }
}
