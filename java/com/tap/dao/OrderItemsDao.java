package com.tap.dao;

import com.tap.model.OrderItems;
import java.util.List;

public interface OrderItemsDao {

    // Insert a new OrderItem
    int insertOrderItem(int orderId, int menuId, int quantity, int itemTotal);

    // Fetch OrderItems by OrderId
    List<OrderItems> fetchOrderItemsByOrderId(int orderId);
}
