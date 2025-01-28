package com.tap.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoimpl.RestaurantDaoImpl;
import com.tap.model.Restaurant;

@WebServlet("/GetAllRestaurants")
public class GetAllRestaurants extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Create an instance of the RestaurantDaoImpl
        RestaurantDaoImpl rdao = new RestaurantDaoImpl();

        // Get all restaurants from the database
        List<Restaurant> rList = rdao.getAllRestaurants();

        // Store the list of restaurants in both session and request attributes
        HttpSession session = req.getSession();
        session.setAttribute("restaurantList", rList);
        req.setAttribute("restaurantList", rList);

        // Forward the request to the JSP page for displaying the restaurants
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}

