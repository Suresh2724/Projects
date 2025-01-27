package com.tap.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.RestaurantDao;
import com.tap.model.Restaurant;

public class RestaurantDaoImpl implements RestaurantDao {

    private static Connection con;
    static ArrayList<Restaurant> restaurantList = new ArrayList<>();

    static String insertRestaurant = "INSERT INTO restaurant (name, imgPath, ratings, eta, cuisineType, address, isActive, restaurantCustomerId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    static String getAllRestaurants = "SELECT * FROM restaurant";
    static String getRestaurantById = "SELECT * FROM restaurant WHERE restaurantId=?";
    static String deleteRestaurantById = "DELETE FROM restaurant WHERE restaurantId=?";
    static String updateAddressById = "UPDATE restaurant SET address=? WHERE restaurantId=?";

    private PreparedStatement pstmt;
    private static Statement stmt;
    private static ResultSet resultSet;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tapfoods", "root", "Suresh@2724");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertRestaurant(Restaurant restaurant) {
        try {
            pstmt = con.prepareStatement(insertRestaurant);
            pstmt.setString(1, restaurant.getName());
            pstmt.setString(2, restaurant.getImgPath());
            pstmt.setFloat(3, restaurant.getRatings()); // Updated to float
            pstmt.setInt(4, restaurant.getEta());
            pstmt.setString(5, restaurant.getCuisineType());
            pstmt.setString(6, restaurant.getAddress());
            pstmt.setString(7, restaurant.getIsActive());
            pstmt.setInt(8, restaurant.getRestaurantCustomerId());

            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(getAllRestaurants);
            restaurantList = (ArrayList<Restaurant>) extractRestaurantListFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurantList;
    }

    @Override
    public Restaurant getRestaurantById(int id) {
        try {
            pstmt = con.prepareStatement(getRestaurantById);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            restaurantList = (ArrayList<Restaurant>) extractRestaurantListFromResultSet(resultSet);
            return restaurantList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteRestaurantById(int id) {
        try {
            pstmt = con.prepareStatement(deleteRestaurantById);
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int updateRestaurantAddressById(int id, String address) {
        try {
            pstmt = con.prepareStatement(updateAddressById);
            pstmt.setString(1, address);
            pstmt.setInt(2, id);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    private List<Restaurant> extractRestaurantListFromResultSet(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                restaurantList.add(new Restaurant(
                    resultSet.getInt("restaurantId"),
                    resultSet.getString("name"),
                    resultSet.getString("imgPath"),
                    resultSet.getFloat("ratings"), // Updated to float
                    resultSet.getInt("eta"),
                    resultSet.getString("cuisineType"),
                    resultSet.getString("address"),
                    resultSet.getString("isActive"),
                    resultSet.getInt("restaurantCustomerId")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurantList;
    }
}
