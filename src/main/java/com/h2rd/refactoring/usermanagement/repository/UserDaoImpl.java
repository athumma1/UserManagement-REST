package com.h2rd.refactoring.usermanagement.repository;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.h2rd.refactoring.usermanagement.model.User;

@Component
public class UserDaoImpl implements UserDao{

	private List<User> users = new ArrayList<>();

	@Override
	public synchronized User saveUser(User user) {
		users.add(user);
		return user;
	}

	@Override
	public List<User> getUsers() {		
		return users;
	}

	@Override
	public synchronized void deleteUser(User userToDelete) {
		User user = findUser(userToDelete.getName());
		users.remove(user);	

	}

	@Override
	public synchronized User updateUser(User userToUpdate) {
		int index = users.indexOf(userToUpdate);
		return users.set(index, userToUpdate);
	}

	@Override
	public User findUser(String name) {
		for (User user : users){
			if (user.getName().equals(name)){
				return user;
			}
		}
		return null;

	}

}



