package com.f.mcround.service;

import com.f.mcround.model.User;
import com.f.mcround.dao.UserDao;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public String registration(User newUser) {
        try {
            boolean isSaved = userDao.registration(newUser);
            if (!isSaved) {
                throw new Exception("Unable to register user");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
        return newUser.getUserName();
    }
}
