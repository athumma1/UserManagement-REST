package com.h2rd.refactoring.usermanagement.controller;

import com.h2rd.refactoring.usermanagement.model.User;
import com.h2rd.refactoring.usermanagement.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam("email") String email){
        if(email !=null){
            final User userByEmail = userManagementService.getUserByEmail(email);
            if (userByEmail != null) {
                return new ResponseEntity<>(userByEmail, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Valid User user){
        userManagementService.saveUser(user);
        return new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        final List<User> allUsers = userManagementService.getAllUsers();
        return new ResponseEntity<List<User>>(allUsers,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody @Valid User user){
        userManagementService.updateUser(user);
        return new ResponseEntity<String>("Successfully Updated", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUserByEmail(@RequestParam("email") String email){
        userManagementService.deleteUser(email);
        return new ResponseEntity<String>("Successfully Deleted", HttpStatus.OK);
    }







}