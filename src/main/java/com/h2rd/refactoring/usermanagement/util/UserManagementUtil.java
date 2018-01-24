package com.h2rd.refactoring.usermanagement.util;


import com.h2rd.refactoring.usermanagement.repository.UserManagementStore;

public class UserManagementUtil {

	public static int findIndex(String name){ 
		for (int i=0; i<UserManagementStore.users.size(); i++){

			if(name.equalsIgnoreCase(UserManagementStore.users.get(i).getName())){
				return i;
			}

		}
		return -1;
	}
}
