package com.h2rd.refactoring.usermanagement.repository;


import org.junit.Before;
import org.junit.Test;

import com.h2rd.refactoring.usermanagement.model.User;
import com.h2rd.refactoring.usermanagement.repository.UserManagementDaoImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UserManagementDaoImplTest {
    private UserManagementDaoImpl userManagementDao;
    
    User user = new User();
    User user1 = new User();
    List<User> usersList = new ArrayList();
    
    @Before
    public void setUp() throws Exception {
        userManagementDao = new UserManagementDaoImpl();
       
        user.setEmail("testuser@gmail.com");
        user.setName("Test User");
        user.setRoles(Arrays.asList("superuser"));
        
        user1.setEmail("testuser1@gmail.com");
        user1.setName("Test User111");
        user1.setRoles(Arrays.asList("superuser11"));
        
        usersList.add(user);
    }

    @Test
    public void getUserByEmail() throws Exception {
       
        userManagementDao.saveUser(user);
        final User userByEmail = userManagementDao.getUserByEmail("testuser@gmail.com");
        assertEquals(user, userByEmail);
    }

    @Test
    public void saveUser() throws Exception {
       
        userManagementDao.saveUser(user);
        final User userByEmail = userManagementDao.getUserByEmail("testuser@gmail.com");
        assertEquals(user, userByEmail);
    }

    @Test
    public void getAllUsers() throws Exception {
    	userManagementDao.saveUser(user);
    	final List<User> users = userManagementDao.getAllUsers();
    	assertEquals(usersList, users);
    }

    @Test
    public void updateUser() throws Exception {
    	userManagementDao.updateUser(user);
    	final User userByEmail = userManagementDao.getUserByEmail("testuser@gmail.com");
    	assertEquals(user, userByEmail);
    	
    }

    @Test
    public void deleteUser() throws Exception {
    	userManagementDao.deleteUser(user.getEmail());
    	final User userByEmail = userManagementDao.getUserByEmail("testusers@gmail.com");
    	assertNull(userByEmail);
    }

}