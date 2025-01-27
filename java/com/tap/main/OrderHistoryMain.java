package com.tap.main;

import com.tap.daoimpl.OrderHistoryDaoImpl;
import com.tap.model.OrderHistory;
import java.util.List;

public class OrderHistoryMain {

    public static void main(String[] args) {
        OrderHistoryDaoImpl oHistoryDao = new OrderHistoryDaoImpl();

        // Inserting a new Order History
//        int insertStatus = oHistoryDao.insertOrderHistory(4);
//        System.out.println("Insert Status: " + insertStatus);

        // Fetching Order History by User ID
        List<OrderHistory> orderHistories = oHistoryDao.fetchOrderHistoryByUserId(1);
        for (OrderHistory oh : orderHistories) {
            System.out.println(oh);
        }
    }
}
