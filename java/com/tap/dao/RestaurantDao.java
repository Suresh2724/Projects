package com.tap.dao;

import java.util.List;
import com.tap.model.Restaurant;

public interface RestaurantDao {

    // Insert a new Restaurant record
    int insertRestaurant(Restaurant restaurant);

    // Retrieve all Restaurants
    List<Restaurant> getAllRestaurants();

    // Retrieve a Restaurant by its ID
    Restaurant getRestaurantById(int id);

    // Delete a Restaurant by its ID
    int deleteRestaurantById(int id);

    // Update the address of a Restaurant by its ID
    int updateRestaurantAddressById(int id, String address);
}
