package com.mary.kiragu.dataaccess;

import java.sql.*;
import com.mary.kiragu.domain.User;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Lenovo
 */
public class LoginDataManager {

    static String jdbcurl = "jdbc:mysql://localhost/AMS";
    static String dbUserName = "root";
    static String dbPassword = "brianna";
    String username;
    String password;
    int userId;

    public boolean addUser(User login) {

        try {
            Connection connection = DriverManager.getConnection(jdbcurl, dbUserName, dbPassword);

            String sqlInsertUser = "INSERT INTO user ( UserName,Password   ) "
                    + "VALUES(?,?)";

            PreparedStatement statement = connection.prepareStatement(sqlInsertUser);

            statement.setString(1, login.getUserName());
            statement.setString(2, login.getPassword());

            System.out.println("query:" + statement.toString());

            int rows = statement.executeUpdate();
            System.out.println(rows + " user has been added to the db");

            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
     public List<User> searchUser(String name) {

        List<User> users = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(jdbcurl, dbUserName, dbPassword);
            String sqlSelectUser = "SELECT * FROM user WHERE UserName like '" + name + "%'  ORDER BY UserId DESC";
            PreparedStatement statement = connection.prepareStatement(sqlSelectUser);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                User user = new User();
                user.setUserId(result.getInt("UserID"));
                user.setUserName(result.getString("username"));
                user.setPassword(result.getString("password"));
               
                
               users.add(user);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return users;
    }


    public static boolean authenticate(String username, String password) {
        try {
            Connection connection = DriverManager.getConnection(jdbcurl, dbUserName, dbPassword);
            String sqlSelectUser = " SELECT UserName,Password from user WHERE UserName=? AND Password=?";
            PreparedStatement statement = connection.prepareStatement(sqlSelectUser);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            boolean found = result.next();

            connection.close();

            return found;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        /* if (username.equals("mary") && password.equals("mary")) {
            return true;
        } else {
            return false;
        }*/
    }

    // public boolean authenticate(String username, String password) {
    // }
}
