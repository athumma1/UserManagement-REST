package com.h2rd.refactoring.usermanagement.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.h2rd.refactoring.usermanagement.model.User;
import com.h2rd.refactoring.usermanagement.service.UserManagementService;
import com.h2rd.refactoring.usermanagement.validator.UserValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserManagementController {
	
	public static Logger log = LoggerFactory.getLogger(UserManagementController.class);

	@Autowired
	private UserManagementService userManagementService;

	@Autowired
	private UserValidator userValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	@PostMapping("/add")		
	public ResponseEntity<User> addUser(@RequestBody @Validated User user) {
		log.info("Creating new User: {}", user);
		userManagementService.addUser(user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody @Validated User user) {		
		log.info("updating user: {}", user);
		user = userManagementService.updateUser(user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}


	@DeleteMapping("/delete")
	public ResponseEntity<User> deleteUser(@RequestBody @Validated User user) {		
		userManagementService.deleteUser(user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}

	@GetMapping("/find")
	public ResponseEntity<List<User>> getUsers() {	
		log.info("Getting All Users");
		List<User> users = userManagementService.getUsers();
		if (users == null || users.isEmpty()) {
			log.info("No Users Found");
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}


	@GetMapping("/search")
	public ResponseEntity<User> findUser(@RequestParam("name") String name) {
		log.info("Getting user with name: {}", name);
		User user = userManagementService.findUser(name);
		if(user == null){
			log.info("User with name {} not found", name);
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
}











