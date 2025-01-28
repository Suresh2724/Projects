package com.tap.model;

public class OrderItems {

    private int orderItemId;  // Primary Key
    private int orderId;      // Order ID (Foreign Key referencing Orders(orderId))
    private int menuId;       // Menu ID (Foreign Key referencing Menu(menuId))
    private int quantity;     // Quantity of the item
    private int itemTotal;    // Total cost of the item (quantity * price)

    // Zero-parameter constructor
    public OrderItems() {
    }

    // Constructor with all parameters
    public OrderItems(int orderItemId, int orderId, int menuId, int quantity, int itemTotal) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.menuId = menuId;
        this.quantity = quantity;
        this.itemTotal = itemTotal;
    }

    // Constructor excluding auto-generated id
    public OrderItems(int orderId, int menuId, int quantity, int itemTotal) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.quantity = quantity;
        this.itemTotal = itemTotal;
    }

    // Getters and Setters
    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(int itemTotal) {
        this.itemTotal = itemTotal;
    }

    // toString method
    @Override
    public String toString() {
        return orderItemId + " " + orderId + " " + menuId + " " + quantity + " " + itemTotal;
    }
}
