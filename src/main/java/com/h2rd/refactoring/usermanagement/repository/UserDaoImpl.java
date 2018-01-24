package com.h2rd.refactoring.usermanagement.repository;


import java.util.List;
import org.springframework.stereotype.Component;
import com.h2rd.refactoring.usermanagement.model.User;
import com.h2rd.refactoring.usermanagement.util.UserManagementUtil;


@Component
public class UserDaoImpl implements UserDao{	

	@Override
	public synchronized User saveUser(User user) {
		UserManagementStore.users.add(user);
		return user;
	}

	@Override
	public List<User> getUsers() {		
		return UserManagementStore.users;
	}

	@Override
	public synchronized void deleteUser(User userToDelete) {
		User user = findUser(userToDelete.getName());
		UserManagementStore.users.remove(user);	

	}

	@Override
	public synchronized User updateUser(User userToUpdate) {
		int index = UserManagementUtil.findIndex(userToUpdate.getName());
		if(index != -1){
				UserManagementStore.users.set(index, userToUpdate);
				return userToUpdate;
		}
		return null;
			
		}
	

	@Override
	public User findUser (String name) {
		int index = UserManagementUtil.findIndex(name);
		return index == -1 ? null:UserManagementStore.users.get(index);		

	}

}



