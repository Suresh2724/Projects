package com.tap.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tap.dao.MenuDao;
import com.tap.daoimpl.MenuDaoImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
        // 1. Get the session for the cart
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        
        // 2. Check if the cart exists or not (if not, create one)
        if(cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        // 3. Fetch request parameters
        String action = req.getParameter("action");
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        
        // 4. Create an object of MenuDao (for fetching the details of the menu item)
        MenuDao menuDao = null;
        try {
            menuDao = new MenuDaoImpl();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        try {
            // 5. Check if the item has to be added to the cart based on the action
            
            if("add".equals(action)) {
                Menu menuItem = menuDao.getMenuById(itemId);
                
                if(menuItem != null) {
                    // Fetch the quantity from the request, default to 1 if not specified
                    int quantity = Integer.parseInt(req.getParameter("quantity"));
                    
                    // Create a CartItem object with the selected quantity
                    CartItem cartItem = new CartItem(menuItem.getMenuId(), menuItem.getRestaurantId(), menuItem.getName(), quantity, menuItem.getPrice());
                    cart.addItem(cartItem);
                }
            }
            else if("update".equals(action)) {    
                // Update the quantity of the item
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                cart.updateItem(itemId, quantity);    
            }
            else if("remove".equals(action)) {
                // Remove the item from the cart
                cart.removeItem(itemId);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        // 6. Redirect to the cart page
        session.setAttribute("cart", cart);
        resp.sendRedirect("cart.jsp");  
    }
}
