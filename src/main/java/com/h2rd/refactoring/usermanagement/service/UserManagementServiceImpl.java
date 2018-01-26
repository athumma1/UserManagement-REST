package com.h2rd.refactoring.usermanagement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2rd.refactoring.usermanagement.model.User;
import com.h2rd.refactoring.usermanagement.repository.UserManagementDao;

import java.util.List;

@Service
public class UserManagementServiceImpl implements UserManagementService{
    @Autowired
    private UserManagementDao userManagmentDao;

    @Override
    public User getUserByEmail(final String email) {
        return userManagmentDao.getUserByEmail(email);
    }

    @Override
    public void saveUser(final User user) {
        userManagmentDao.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userManagmentDao.getAllUsers();
    }

    @Override
    public void updateUser(final User user) {
        userManagmentDao.updateUser(user);
    }

    @Override
    public void deleteUser(final String email) {
        userManagmentDao.deleteUser(email);
    }
}