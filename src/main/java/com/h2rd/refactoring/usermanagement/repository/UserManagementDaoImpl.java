package com.h2rd.refactoring.usermanagement.repository;


import org.springframework.stereotype.Repository;

import com.h2rd.refactoring.usermanagement.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserManagementDaoImpl implements UserManagementDao {

    private static final Map<String, User> usersMap = new ConcurrentHashMap<>();

    @Override
    public User getUserByEmail(final String email) {
        if(usersMap.containsKey(email)){
            return usersMap.get(email);
        }
        return null;
    }

    @Override
    public void saveUser(final User user) {
        usersMap.put(user.getEmail(), user);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(usersMap.values());
    }

    @Override
    public void updateUser(final User user) {
        usersMap.put(user.getEmail(), user);
    }

    @Override
    public void deleteUser(final String email) {
        if (usersMap.containsKey(email)) {
            usersMap.remove(email);
        }
    }
}