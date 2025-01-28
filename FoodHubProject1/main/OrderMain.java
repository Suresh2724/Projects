package com.tap.main;

import java.util.ArrayList;
import com.tap.daoimpl.OrderDaoImpl;
import com.tap.model.Orders;

public class OrderMain {

    public static void main(String[] args) {

        OrderDaoImpl odao = new OrderDaoImpl();

        // Inserting a new Order
//        int insertStatus = odao.insertOrder(14, 2, 500, "creditCard", "confirm");
//        System.out.println("Insert Status: " + insertStatus);

        // Fetching an Order by ID
        Orders fetchedOrder = odao.fetchOrderById(4);
        System.out.println("Fetched Order: " + fetchedOrder);

        // Updating Order status
//        int updateStatus = odao.updateOrderStatus(4, "delivered");
//        System.out.println("Update Status: " + updateStatus);
    }
}
