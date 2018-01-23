package com.h2rd.refactoring.usermanagement.service;


import java.util.List;

import org.springframework.stereotype.Component;

import com.h2rd.refactoring.usermanagement.model.User;


public interface UserManagementService {
	
	User addUser(User user);
	List<User> getUsers();
	void deleteUser(User userToDelete);
	User updateUser(User userToUpdate);
	User findUser(String name);

}
