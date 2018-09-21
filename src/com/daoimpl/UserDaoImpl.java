package com.daoimpl;

import com.dao.UserDao;
import com.entities.User;
import com.util.ConnectionConfiguration;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    @Override
    public void createUserTable() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS user (username varchar(55) NOT NULL PRIMARY KEY UNIQUE, password varchar(55) NOT NULL)");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try{
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String insert(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String message = "Database not connected";

        try{
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO user (username, password)" +
                    "VALUES (?, ?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            try {
                preparedStatement.executeUpdate();
                message = "Successfully signed up";
            } catch (SQLIntegrityConstraintViolationException e) {
                message = "Error: username already exists";
                System.out.println(message);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return message;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        boolean success = false;
        User user = new User();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
            if(user.getPassword() != null)
                if(user.getPassword().equals(password))
                    success = true;
        } catch(Exception e) {
            e.printStackTrace();
        }finally{
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }
}
