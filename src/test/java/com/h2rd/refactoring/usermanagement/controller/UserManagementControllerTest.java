package com.h2rd.refactoring.usermanagement.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.h2rd.refactoring.usermanagement.model.User;
import com.h2rd.refactoring.usermanagement.service.UserManagementService;

import junit.framework.Assert;

public class UserManagementControllerTest {
	
	@Autowired
	private UserManagementService userManagementService;
	
	@Test
	public void addtest(){
		User user = new User();
		List<String> roles = new ArrayList();  
        roles.add("A");  
        roles.add("B");  
        roles.add("C");  
      
		user.setName("Abhi");
		user.setEmail("ajhdasd@gmail.com");
		user.setRoles(roles);	
		User user1 = userManagementService.addUser(user);
		
		assertEquals("Abhi", user1.getName());
		assertEquals("ajhdasd@gmail.com", user1.getEmail());
		assertEquals(roles, user1.getRoles());
		
		
	}

}
