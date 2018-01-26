package com.h2rd.refactoring.usermanagement.service;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.h2rd.refactoring.usermanagement.model.User;
import com.h2rd.refactoring.usermanagement.repository.UserManagementDao;
import com.h2rd.refactoring.usermanagement.service.UserManagementServiceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class UserManagementServiceImplTest {
    @Mock
    private UserManagementDao userManagmentDao;
    @InjectMocks
    private UserManagementServiceImpl userManagementService;

    User user = new User();
    User user1 = new User();
    
    List<User> users = new ArrayList();
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
       
        user.setName("Test User");
        user.setEmail("testuser@gmail.com");
        
        user1.setName("Test User1");
        user1.setEmail("testuser11@gmail.com");
        
        users.add(user);
    }

    @Test
    public void getUserByEmail() throws Exception {
        
        when(userManagmentDao.getUserByEmail("testuser@gmail.com")).thenReturn(user);
        final User userByEmail = userManagementService.getUserByEmail("testuser@gmail.com");
        assertEquals(user, userByEmail);
        verify(userManagmentDao, times(1)).getUserByEmail("testuser@gmail.com");
    }

    @Test
    public void saveUser() throws Exception {
    	
    	doNothing().when(userManagmentDao).saveUser(user);
    	userManagementService.saveUser(user);
    	verify(userManagmentDao, times(1)).saveUser(user);
    	
    }

    @Test
    public void getAllUsers() throws Exception {
    	when(userManagmentDao.getAllUsers()).thenReturn(users);
    	final List<User> allUsers = userManagementService.getAllUsers();
    	assertEquals(users, allUsers);
    	verify(userManagmentDao, times(1)).getAllUsers();
    }

    @Test
    public void updateUser() throws Exception {
    	doNothing().when(userManagmentDao).updateUser(user);
    	userManagementService.updateUser(user);
    	verify(userManagmentDao, times(1)).updateUser(user);
    }

    @Test
    public void deleteUser() throws Exception {
    	doNothing().when(userManagmentDao).deleteUser(user.getEmail());
    	userManagementService.deleteUser(user.getEmail());
    	verify(userManagmentDao, times(1)).deleteUser(user.getEmail());
    	
    }

}