package com.dao;

import com.entities.User;

public interface UserDao {
    void createUserTable();

    String insert(User user);

    boolean checkLogin(String username, String password);
}
