package com.f.mcround.dao;

import com.f.mcround.DBSimulator;
import com.f.mcround.model.User;

import java.util.HashSet;

public class UserDao {

    private DBSimulator dbSimulator;

    public UserDao(DBSimulator dbSimulator) {
        this.dbSimulator = dbSimulator;
    }

    public boolean registration(User user) {
        try {
            if (user.getSubscriptionList() == null)
                user.setSubscriptionList(new HashSet<>());
            dbSimulator.getUsers().put(user.getUserName(), user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
