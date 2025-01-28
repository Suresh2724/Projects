package com.tap.controllers;

import com.tap.dao.MenuDao;
import com.tap.daoimpl.MenuDaoImpl;
import com.tap.model.Menu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Get the restaurantId parameter from the request
            String restaurantIdStr = req.getParameter("restaurantId");
            if (restaurantIdStr != null && !restaurantIdStr.isEmpty()) {
                // Convert to integer
                int restaurantId = Integer.parseInt(restaurantIdStr);

                // Create an instance of MenuDaoImpl
                MenuDao menuDao = new MenuDaoImpl();

                // Fetch the menu items for the given restaurantId
                List<Menu> menuItems = menuDao.fetchMenuByRestaurantId(restaurantId);

                // Check if menuItems is empty or null
                if (menuItems != null && !menuItems.isEmpty()) {
                    // Set the menu items in the request object
                    req.setAttribute("menuItems", menuItems);

                    // Forward to the menu.jsp page
                    req.getRequestDispatcher("menu.jsp").forward(req, resp);
                } else {
                    // If no menu items are found, set error message in the request
                    req.setAttribute("error", "No menu items found for this restaurant.");
                    req.getRequestDispatcher("error.jsp").forward(req, resp);
                }
            } else {
                // Handle missing or invalid restaurantId parameter
                req.setAttribute("error", "Invalid restaurant ID.");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
            req.setAttribute("error", "An error occurred while fetching the menu.");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
