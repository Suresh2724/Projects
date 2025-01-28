package com.tap.model;

public class CartItem {
    private int itemId;
    private int restaurantId;
    private String name;
    private int quantity;
    private double price;

    // Constructor
    public CartItem(int itemId, int restaurantId, String name, int quantity, double price) {
        this.itemId = itemId;
        this.restaurantId = restaurantId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter and Setter methods
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Additional helper methods
    public double getTotalPrice() {
        return price * quantity;
    }
}
