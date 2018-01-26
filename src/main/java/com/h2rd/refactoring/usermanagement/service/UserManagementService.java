package com.h2rd.refactoring.usermanagement.service;


import java.util.List;

import com.h2rd.refactoring.usermanagement.model.User;

public interface UserManagementService {
    User getUserByEmail(String email);
    void saveUser(User user);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(String user);
}