package com.tap.main;

import com.tap.daoimpl.OrderItemsDaoImpl;
import com.tap.model.OrderItems;
import java.util.List;

public class OrderItemsMain {

    public static void main(String[] args) {

        OrderItemsDaoImpl oiDao = new OrderItemsDaoImpl();

        // Inserting a new OrderItem (Uncomment to test insert functionality)
        // int insertStatus = oiDao.insertOrderItem(1, 1, 5, 300); // Example: orderId=1, menuId=1, quantity=5, itemTotal=300
        // System.out.println("Insert Status: " + insertStatus);

        // Fetching OrderItems by OrderId (for example, orderId=1)
        List<OrderItems> orderItemsList = oiDao.fetchOrderItemsByOrderId(1);

        if (orderItemsList != null && !orderItemsList.isEmpty()) {
            System.out.println("Fetched OrderItems for OrderId 1:");
            for (OrderItems orderItem : orderItemsList) {
                System.out.println(orderItem); // Display each order item
            }
        } else {
            System.out.println("No order items found for OrderId 1.");
        }
    }
}
