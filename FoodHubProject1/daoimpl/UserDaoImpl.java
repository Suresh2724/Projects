package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.UserDao;
import com.tap.encrypt_decrypt.MyDecrypt;
import com.tap.model.User;

public class UserDaoImpl implements UserDao {

    int x = -1;
    User user;
    private static Connection con;

    static ArrayList<User> userList = new ArrayList<>();
    static String insertUser = "INSERT INTO user (name, email, phoneNumber, address, userName, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
    static String getAll = "SELECT * FROM user";
    static String getUserById = "SELECT * FROM user WHERE userId=?";
    static String deleteUserById = "DELETE FROM user WHERE userId=?";
    static String updateAddressById = "UPDATE user SET address=? WHERE userId=?";
    static String fetchByEmail = "SELECT * FROM user WHERE email=?";  // SQL query to fetch user by email

    private PreparedStatement pstmt;

    private static Statement stmt;

    private static ResultSet resultSet;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tapfoods", "root", "Suresh@2724");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int insertUser(User user) {
        try {
            pstmt = con.prepareStatement(insertUser);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPhoneNumber());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getUserName());
            pstmt.setString(6, user.getPassword());
            pstmt.setString(7, user.getRole());
            x = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public List<User> getAllUsers() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(getAll);
            userList = (ArrayList<User>) extractUserListFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User getUserById(int id) {
        try {
            pstmt = con.prepareStatement(getUserById);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            userList = (ArrayList<User>) extractUserListFromResultSet(resultSet);
            user = userList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public int deleteUserById(int id) {
        try {
            pstmt = con.prepareStatement(deleteUserById);
            pstmt.setInt(1, id);
            x = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public int updateUserById(int id, String address) {
        try {
            pstmt = con.prepareStatement(updateAddressById);
            pstmt.setInt(2, id);
            pstmt.setString(1, address);
            x = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public User fetchByEmail(String email) {
        try {
            pstmt = con.prepareStatement(fetchByEmail);
            pstmt.setString(1, email);
            resultSet = pstmt.executeQuery();
            
            if (resultSet.next()) {
                // Decrypt other fields but leave password as it is
                String decryptedName = MyDecrypt.decrypt(resultSet.getString("name"));
                String decryptedEmail = MyDecrypt.decrypt(resultSet.getString("email"));
                String decryptedPhoneNumber = MyDecrypt.decrypt(resultSet.getString("phoneNumber"));
                String decryptedAddress = MyDecrypt.decrypt(resultSet.getString("address"));
                String decryptedUserName = MyDecrypt.decrypt(resultSet.getString("userName"));
                String encryptedPassword = resultSet.getString("password");  // Keep it encrypted
                
                // Return a User object with decrypted data, but encrypted password
                return new User(
                    resultSet.getInt("userId"),
                    decryptedName,
                    decryptedEmail,
                    decryptedPhoneNumber,
                    decryptedAddress,
                    decryptedUserName,
                    encryptedPassword, // Don't decrypt password
                    resultSet.getString("role"),
                    resultSet.getTimestamp("createdDate"),
                    resultSet.getTimestamp("lastLogin")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if no user found
    }




    static List<User> extractUserListFromResultSet(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getInt("userId"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("address"),
                        resultSet.getString("userName"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getTimestamp("createdDate"),
                        resultSet.getTimestamp("lastLogin")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
}
