package com.h2rd.refactoring.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.h2rd.refactoring.usermanagement.model.User;
import com.h2rd.refactoring.usermanagement.repository.UserDao;

@Service
@Component
public class UserManagementServiceImpl implements UserManagementService{

	@Autowired
	private UserDao userDao;

	@Override
	public User addUser(User user) {
		return userDao.saveUser(user);

	}

	@Override
	public List<User> getUsers() {
		return userDao.getUsers();

	}

	@Override
	public void deleteUser(User userToDelete) {		
		userDao.deleteUser(userToDelete);
		return ;
	}

	@Override
	public User updateUser(User userToUpdate) {		
		return userDao.updateUser(userToUpdate);
	}

	@Override
	public User findUser(String name) {
		return userDao.findUser(name);

	}



}
