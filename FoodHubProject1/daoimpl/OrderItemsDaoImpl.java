package com.tap.daoimpl;

import java.sql.*;
import com.tap.dao.OrderItemsDao;
import com.tap.model.OrderItems;
import java.util.ArrayList;
import java.util.List;

public class OrderItemsDaoImpl implements OrderItemsDao {

    private static Connection con;
    private PreparedStatement pstmt;
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
    public int insertOrderItem(int orderId, int menuId, int quantity, int itemTotal) {
        String insertOrderItemQuery = "INSERT INTO OrderItems (orderId, menuId, quantity, itemTotal) VALUES (?, ?, ?, ?)";
        try {
            pstmt = con.prepareStatement(insertOrderItemQuery);
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, menuId);
            pstmt.setInt(3, quantity);
            pstmt.setInt(4, itemTotal);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Updated method to fetch order items by orderId
    @Override
    public List<OrderItems> fetchOrderItemsByOrderId(int orderId) {
        String fetchOrderItemsQuery = "SELECT * FROM OrderItems WHERE orderId=?";
        List<OrderItems> orderItemsList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(fetchOrderItemsQuery);
            pstmt.setInt(1, orderId);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                orderItemsList.add(new OrderItems(
                    resultSet.getInt("orderItemId"),
                    resultSet.getInt("orderId"),
                    resultSet.getInt("menuId"),
                    resultSet.getInt("quantity"),
                    resultSet.getInt("itemTotal")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderItemsList;
    }
}
