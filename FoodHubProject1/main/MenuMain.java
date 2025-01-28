package com.tap.main;

import java.util.ArrayList;
import java.util.List;

import com.tap.daoimpl.MenuDaoImpl;
import com.tap.model.Menu;

public class MenuMain {

    public static void main(String[] args) {

        MenuDaoImpl mdao = new MenuDaoImpl();

        // Example: Insert a new Menu
        // Menu m = new Menu("Pizza", 200, "Delicious Margherita Pizza", "/path/to/pizza.jpg", "yes", 1);
        // int status = mdao.insertMenu(m);
        // System.out.println(status);

        // Retrieve all Menus for a specific restaurant
        ArrayList<Menu> menus = (ArrayList<Menu>) mdao.fetchMenuByRestaurantId(56);
        for (Menu m : menus) {
            System.out.println(m);
        }

        // Retrieve a Menu by its ID
        // Menu menu = mdao.getMenuById(56);
        // System.out.println(menu);

        // Delete a Menu by its ID
        // int status = mdao.deleteMenuById(57);
        // System.out.println(status);

        // Update a Menu item by its ID
        // int status = mdao.updateMenuById(58, "no");
        // System.out.println(status);
    }
}
