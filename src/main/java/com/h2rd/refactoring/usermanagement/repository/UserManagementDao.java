package com.h2rd.refactoring.usermanagement.repository;

import java.util.List;
import com.h2rd.refactoring.usermanagement.model.User;

public interface UserManagementDao {
	User getUserByEmail(String email);
	void saveUser(User user);
	List<User> getAllUsers();
	void updateUser(User user);
	void deleteUser(String email);
}