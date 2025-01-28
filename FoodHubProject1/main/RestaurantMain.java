package com.tap.main;

import java.util.ArrayList;
import java.util.List;

import com.tap.daoimpl.RestaurantDaoImpl;
import com.tap.model.Restaurant;

public class RestaurantMain {

    public static void main(String[] args) {
        
//        Restaurant r = new Restaurant("The Food Place", "/path/to/image.jpg", 4, 30, "Italian", "123 Street, City", "Yes", 101);
        
        RestaurantDaoImpl rdao = new RestaurantDaoImpl();
        
//        int status = rdao.insertRestaurant(r);
//        System.out.println(status);
        
        ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) rdao.getAllRestaurants();
        
        for (Restaurant r : restaurants) {
            System.out.println(r);
        }
        
//        Restaurant restaurant = rdao.getRestaurantById(4);
//        System.out.println(restaurant);
        
//        int status = rdao.deleteRestaurantById(13);
//        System.out.println(status);
        
//        int status = rdao.updateRestaurantAddressById(1, "Goluladinne, Bpl");
//        System.out.println(status);
    }
}
