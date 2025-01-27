package com.tap.daoimpl;

import java.sql.*;
import com.tap.dao.OrderDao;
import com.tap.model.Orders;

public class OrderDaoImpl implements OrderDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/tapfoods";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Suresh@2724";

    // SQL Queries
    private static final String INSERT_ORDER = 
        "INSERT INTO `orders` (restaurantId, userId, totalAmount, modeOfPayment, status) VALUES (?, ?, ?, ?, ?)";
    private static final String FETCH_ORDER_BY_ID = 
        "SELECT * FROM `orders` WHERE orderId=?";
    private static final String UPDATE_ORDER_STATUS = 
        "UPDATE `orders` SET status=? WHERE orderId=?";

    // Get Database Connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    @Override
    public int insertOrder(int restaurantId, int userId, int totalAmount, String modeOfPayment, String status) {
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, restaurantId);
            pstmt.setInt(2, userId);
            pstmt.setInt(3, totalAmount);
            pstmt.setString(4, modeOfPayment);
            pstmt.setString(5, status);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Return the generated orderId
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // You can replace this with a proper logging framework
        }
        return -1;
    }

    @Override
    public Orders fetchOrderById(int oId) {
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(FETCH_ORDER_BY_ID)) {

            pstmt.setInt(1, oId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    return new Orders(
                        resultSet.getInt("orderId"),
                        resultSet.getInt("restaurantId"),
                        resultSet.getInt("userId"),
                        resultSet.getInt("totalAmount"),
                        resultSet.getString("modeOfPayment"),
                        resultSet.getString("status"),
                        resultSet.getTimestamp("order_date") // Including the order_date from the result
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging
        }
        return null;
    }

    @Override
    public int updateOrderStatus(int oId, String status) {
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(UPDATE_ORDER_STATUS)) {

            pstmt.setString(1, status);
            pstmt.setInt(2, oId);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging
        }
        return -1;
    }
}
