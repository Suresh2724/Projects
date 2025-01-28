package com.tap.model;

public class Restaurant {

    private int restaurantId;           // Primary Key
    private String name;                // Restaurant name
    private String imgPath;             // Path to the image
    private float ratings;              // Ratings between 0 and 5 (updated to float)
    private int eta;                    // Estimated time of arrival
    private String cuisineType;         // Type of cuisine
    private String address;             // Restaurant address
    private String isActive;            // 'yes' or 'no'
    private int restaurantCustomerId;   // Foreign Key referencing User(userId)

    // Zero-parameter constructor
    public Restaurant() {
    }

    // Constructor with all parameters
    public Restaurant(int restaurantId, String name, String imgPath, float ratings, int eta, String cuisineType, String address, String isActive, int restaurantCustomerId) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.imgPath = imgPath;
        this.ratings = ratings;
        this.eta = eta;
        this.cuisineType = cuisineType;
        this.address = address;
        this.isActive = isActive;
        this.restaurantCustomerId = restaurantCustomerId;
    }

    // Constructor excluding auto-generated id
    public Restaurant(String name, String imgPath, float ratings, int eta, String cuisineType, String address, String isActive, int restaurantCustomerId) {
        this.name = name;
        this.imgPath = imgPath;
        this.ratings = ratings;
        this.eta = eta;
        this.cuisineType = cuisineType;
        this.address = address;
        this.isActive = isActive;
        this.restaurantCustomerId = restaurantCustomerId;
    }

    // Getters and Setters
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public int getRestaurantCustomerId() {
        return restaurantCustomerId;
    }

    public void setRestaurantCustomerId(int restaurantCustomerId) {
        this.restaurantCustomerId = restaurantCustomerId;
    }

    // toString method
    @Override
    public String toString() {
        return restaurantId + " " + name + " " + imgPath + " " + ratings + " " + eta + " " + cuisineType + " " + address + " " + isActive + " " + restaurantCustomerId;
    }
}
