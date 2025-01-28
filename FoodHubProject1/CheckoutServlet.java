package com.tap.controllers;

import com.tap.daoimpl.OrderDaoImpl;
import com.tap.daoimpl.OrderItemsDaoImpl;
import com.tap.daoimpl.OrderHistoryDaoImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/checkoutServlet")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve user and cart details from the session
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");

        if (user != null && cart != null && !cart.getItems().isEmpty()) {
            // Get the form inputs
            String address = req.getParameter("address");
            String modeOfPayment = req.getParameter("modeOfPayment");

            // Calculate total amount
            double totalAmount = 0;
            // Assuming the restaurantId for the order is taken from the first cart item
            int restaurantId = cart.getItems().values().iterator().next().getRestaurantId();

            for (CartItem item : cart.getItems().values()) {
                totalAmount += item.getPrice() * item.getQuantity();
            }

            // Create an order using the OrderDaoImpl
            OrderDaoImpl orderDao = new OrderDaoImpl();
            int orderId = orderDao.insertOrder(restaurantId, user.getUserId(), (int) totalAmount, modeOfPayment, "pending");

            // Insert order items into OrderItems table
            OrderItemsDaoImpl orderItemsDao = new OrderItemsDaoImpl();
            for (CartItem item : cart.getItems().values()) {
                int itemTotal = (int) (item.getPrice() * item.getQuantity());
                orderItemsDao.insertOrderItem(orderId, item.getItemId(), item.getQuantity(), itemTotal);
            }

            // Insert into OrderHistory with the restaurantId
            OrderHistoryDaoImpl orderHistoryDao = new OrderHistoryDaoImpl();
            int orderHistoryResult = orderHistoryDao.insertOrderHistory(orderId, user.getUserId(), new Date(), (int) totalAmount, "pending", restaurantId);

            if (orderHistoryResult > 0) {
                // Clear the cart after placing the order
                cart.clear();

                // Redirect to order confirmation page
                resp.sendRedirect("orderconfirmation.jsp");
            } else {
                // Handle the case when inserting into OrderHistory failed
                resp.sendRedirect("error.jsp");
            }
        } else {
            // Handle the case when user or cart is null
            resp.sendRedirect("home.jsp");
        }
    }
}
