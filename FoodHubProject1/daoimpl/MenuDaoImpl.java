package com.tap.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDao;
import com.tap.model.Menu;

public class MenuDaoImpl implements MenuDao {

    private static final String INSERT_MENU = "INSERT INTO Menu (name, price, description, imgPath, isAvailable, restaurantId) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_RESTAURANT_MENU = "SELECT * FROM Menu WHERE restaurantId=?";
    private static final String GET_MENU_BY_ID = "SELECT * FROM Menu WHERE menuId=?";
    private static final String DELETE_MENU_BY_ID = "DELETE FROM Menu WHERE menuId=?";
    private static final String UPDATE_MENU_BY_ID = "UPDATE Menu SET isAvailable=? WHERE menuId=?";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tapfoods";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Suresh@2724";

    @Override
    public int insertMenu(Menu menu) {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(INSERT_MENU)) {
            pstmt.setString(1, menu.getName());
            pstmt.setInt(2, menu.getPrice());
            pstmt.setString(3, menu.getDescription());
            pstmt.setString(4, menu.getImgPath());
            pstmt.setString(5, menu.getIsAvailable());
            pstmt.setInt(6, menu.getRestaurantId());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Menu> fetchMenuByRestaurantId(int restaurantId) {
        List<Menu> menuList = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(GET_ALL_RESTAURANT_MENU)) {
            pstmt.setInt(1, restaurantId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                menuList = extractMenuListFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public Menu getMenuById(int id) {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(GET_MENU_BY_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                List<Menu> menuList = extractMenuListFromResultSet(resultSet);
                if (!menuList.isEmpty()) {
                    return menuList.get(0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteMenuById(int id) {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(DELETE_MENU_BY_ID)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int updateMenuById(int id, String isAvailable) {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(UPDATE_MENU_BY_ID)) {
            pstmt.setString(1, isAvailable);
            pstmt.setInt(2, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private List<Menu> extractMenuListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Menu> menuList = new ArrayList<>();
        while (resultSet.next()) {
            menuList.add(new Menu(
                resultSet.getInt("menuId"),
                resultSet.getString("name"),
                resultSet.getInt("price"),
                resultSet.getString("description"),
                resultSet.getString("imgPath"),
                resultSet.getString("isAvailable"),
                resultSet.getInt("restaurantId")
            ));
        }
        return menuList;
    }
}
