package com.h2rd.refactoring.usermanagement.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.h2rd.refactoring.usermanagement.model.User;


public interface UserDao {

	User saveUser(User user);
	List<User> getUsers();
	void deleteUser(User userToDelete);
	User updateUser(User userToUpdate);
	User findUser(String name);


}
