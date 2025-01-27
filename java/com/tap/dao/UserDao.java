package com.tap.dao;

import java.util.List;
import com.tap.model.User;

public interface UserDao {

    int insertUser(User user);

    List<User> getAllUsers();

    User getUserById(int id);

    int deleteUserById(int id);

    int updateUserById(int id, String address);
    
    // Change return type to User instead of String
    User fetchByEmail(String email);
}
