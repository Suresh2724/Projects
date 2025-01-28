package com.tap.dao;

import com.tap.model.Orders;

public interface OrderDao {

    // Insert a new Order
    int insertOrder(int restaurantId, int userId, int totalAmount, String modeOfPayment, String status);

    // Fetch Order by Order ID
    Orders fetchOrderById(int oId);

    // Update Order status by Order ID
    int updateOrderStatus(int oId, String status);
}
