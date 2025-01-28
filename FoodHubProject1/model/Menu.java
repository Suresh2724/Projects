package com.tap.model;

public class Menu {

    private int menuId;           // Primary Key
    private String name;          // Menu item name
    private int price;            // Price of the menu item
    private String description;   // Description of the menu item
    private String imgPath;       // Path to the image of the menu item
    private String isAvailable;   // 'yes' or 'no'
    private int restaurantId;     // Foreign Key referencing Restaurant(restaurantId)

    // Zero-parameter constructor
    public Menu() {
    }

    // Constructor with all parameters
    public Menu(int menuId, String name, int price, String description, String imgPath, String isAvailable, int restaurantId) {
        this.menuId = menuId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imgPath = imgPath;
        this.isAvailable = isAvailable;
        this.restaurantId = restaurantId;
    }

    // Constructor excluding auto-generated id
    public Menu(String name, int price, String description, String imgPath, String isAvailable, int restaurantId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imgPath = imgPath;
        this.isAvailable = isAvailable;
        this.restaurantId = restaurantId;
    }

    // Getters and Setters
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    // toString method
    @Override
    public String toString() {
        return menuId + " " + name + " " + price + " " + description + " " + imgPath + " " + isAvailable + " " + restaurantId;
    }
}
