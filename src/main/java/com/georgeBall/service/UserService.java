package com.georgeBall.service;

import com.georgeBall.dao.UserDao;
import com.georgeBall.model.User;

public class UserService {
    private UserDao userDao = new UserDao();

    public void registerUser(String username, String password) {
        User user = new User(username, password);
        userDao.save(user);
        System.out.println("User registered: " + username); // Debug statement
    }

    public User loginUser(String username, String password) {
        User user = userDao.find(username);
        if (user != null && password.equals(user.getPassword())) {
            System.out.println("Login successful for user: " + username); // Debug statement
            return user;
        }
        System.out.println("Login failed for user: " + username + " with password: " + password); // Debug statement
        return null;
    }
}
