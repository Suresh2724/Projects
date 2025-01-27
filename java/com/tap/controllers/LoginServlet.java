package com.tap.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoimpl.UserDaoImpl;
import com.tap.encrypt_decrypt.MyEncrypt;
import com.tap.model.User;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = MyEncrypt.encrypt(req.getParameter("email"));
        String password = MyEncrypt.encrypt(req.getParameter("password"));

        UserDaoImpl userdao = new UserDaoImpl();
        User user = userdao.fetchByEmail(email);

        if (user != null && password.equals(user.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("home.jsp");
        } else {
            resp.sendRedirect("failure.jsp");
        }
    }
}
