package com.tap.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoimpl.OrderItemsDaoImpl;
import com.tap.model.OrderItems;

@WebServlet("/viewdetailsServlet") // Map to /viewDetails
public class ViewDetailsServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Retrieve the orderId from the request
            String orderIdStr = req.getParameter("orderId");
            if (orderIdStr != null && !orderIdStr.isEmpty()) {
                int orderId = Integer.parseInt(orderIdStr);

                // Fetch items for the given orderId
                OrderItemsDaoImpl orderItemsDao = new OrderItemsDaoImpl();
                List<OrderItems> orderItemsList = orderItemsDao.fetchOrderItemsByOrderId(orderId);

                // Store the list of items in the session
                HttpSession session = req.getSession();
                session.setAttribute("orderItemsList", orderItemsList);

                // Redirect to the JSP page to display the items
                resp.sendRedirect("showItems.jsp");
            } else {
                // If orderId is missing or invalid, show an error message
                req.setAttribute("error", "Invalid order ID.");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions gracefully
            req.setAttribute("error", "An error occurred while fetching order details.");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
