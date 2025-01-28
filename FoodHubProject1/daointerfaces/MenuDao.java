package com.tap.dao;

import java.util.List;
import com.tap.model.Menu;

public interface MenuDao {

    // Insert a new Menu record
    int insertMenu(Menu menu);

    // Retrieve all Menu items for a specific restaurant
    List<Menu> fetchMenuByRestaurantId(int restaurantId);

    // Retrieve a Menu item by its ID
    Menu getMenuById(int id);

    // Delete a Menu item by its ID
    int deleteMenuById(int id);

    // Update a Menu item by its ID
    int updateMenuById(int id, String isAvailable);
}
