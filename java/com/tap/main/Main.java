package com.tap.main;

import java.util.ArrayList;
import java.util.List;

import com.tap.daoimpl.UserDaoImpl;
import com.tap.model.User;

public class Main {

	public static void main(String[] args) {
		
//		User u=new User("suresh", "suresh@example.com", 96189650, "Banglore", "suresh123", "Suresh@2724", "customer");
		
		
		UserDaoImpl udao = new UserDaoImpl();
		
//		int status=udao.insertUser(u);
//		System.out.println(status);
	
		
		ArrayList<User> users = (ArrayList<User>) udao.getAllUsers();
		
		for(User u:users) {
			System.out.println(u);
		}
	
		
//		User user=udao.getUserById(4);
//		System.out.println(user);
		
		
//		int status=udao.deleteUserById(13);
//		System.out.println(status);
		
		
//		int status=udao.updateUserById(1, "Gokuladinne,Bpl");
//		System.out.println(status);
	}
	

}
