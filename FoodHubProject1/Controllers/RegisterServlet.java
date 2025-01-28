package com.tap.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoimpl.UserDaoImpl;
import com.tap.encrypt_decrypt.MyEncrypt;
import com.tap.model.User;
@WebServlet("/RegisterServletHome")
public class RegisterServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 String encryptedPassword = MyEncrypt.encrypt(req.getParameter("password"));
	        String encryptedConfirmPassword = MyEncrypt.encrypt(req.getParameter("cpassword"));

	        if (encryptedPassword.equals(encryptedConfirmPassword)) {

	            // Create DAO instance
	            UserDaoImpl udao = new UserDaoImpl();

	            // Encrypt all sensitive data before storing
	            String encryptedName = MyEncrypt.encrypt(req.getParameter("name"));
	            String encryptedEmail = MyEncrypt.encrypt(req.getParameter("email"));
	            String encryptedPhoneNumber = MyEncrypt.encrypt(req.getParameter("phonenumber"));
	            String encryptedAddress = MyEncrypt.encrypt(req.getParameter("address"));
	            String encryptedUserName = MyEncrypt.encrypt(req.getParameter("username"));

	            // Create a new User object and populate it with encrypted data
	            User user = new User();
	            user.setName(encryptedName);
	            user.setEmail(encryptedEmail);
	            user.setPhoneNumber(encryptedPhoneNumber);
	            user.setAddress(encryptedAddress);
	            user.setUserName(encryptedUserName);
	            user.setPassword(encryptedPassword); // Store the encrypted password
	            user.setRole(req.getParameter("role")); // Role remains plaintext

	            
	            // Insert the user into the database
	            int result = udao.insertUser(user);

	            if (result == 1) {
	            	// Decrypt data for display (optional, if needed later)
	               // String decryptedName = MyDecrypt.decrypt(user.getName());
	                //String decryptedEmail = MyDecrypt.decrypt(user.getEmail());
	                //String decryptedAddress = MyDecrypt.decrypt(user.getAddress());
	                //String decryptedUserName = MyDecrypt.decrypt(user.getUserName());

	                // Example: Print decrypted data to console (can be removed)
	                //System.out.println("Decrypted Name: " + decryptedName);
	                //System.out.println("Decrypted Email: " + decryptedEmail);
	                //System.out.println("Decrypted Address: " + decryptedAddress);
	                //System.out.println("Decrypted UserName: " + decryptedUserName);
	                resp.sendRedirect("home.jsp");
	            } else {
	                resp.sendRedirect("failure.jsp");
	            }

	        } else {
	            // Redirect to a mismatch page if passwords don't match
	            resp.sendRedirect("passwordMissmatch.jsp");
	        }
		
	}
}
