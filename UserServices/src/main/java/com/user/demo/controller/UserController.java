package com.user.demo.controller;

import java.util.List;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.demo.dto.UserCredentialsDto;
import com.user.demo.dto.UserRequest;
import com.user.demo.entity.User;
import com.user.demo.exception.InvalidCredentialsException;
import com.user.demo.exception.UserNotFoundException;
import com.user.demo.service.UserService;

import jakarta.validation.Valid;


/*
 * Author: THARUN A
 * Description: Contains the controller end-points for User Services
 * Actions: Add, Update, Delete, List users, List user projects, List user tickets
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

	// Injecting the UserService dependency
	private UserService userservice;



    // Constructor-based dependency injection
	public UserController(UserService userservice) {
		super();
		this.userservice = userservice;
	}

	// Endpoint to get all users
	@GetMapping("/getAll")
	public List<User> getAllUsers() {
		return userservice.getAll();
	}
	
	 // Endpoint to get projects of a user
	@GetMapping("/getProjects")
	public List<User> getProjects(){
		return userservice.getUserProjects();
	}
	
	// Endpoint to get tickets of a user
	@GetMapping("/getTickets")
	public List<User> getTickets(){
		return userservice.getUserTickets();
	}

	// Endpoint to get a single user by their ID
	@GetMapping("/getOne/{id}")
	public User getOneUser(@PathVariable Integer id) throws UserNotFoundException {
		return userservice.getOne(id);
	}

	// Endpoint to add a new user
	@PostMapping("/addNew")
	public String addNewUser(@RequestBody @Valid UserRequest userRequest) {
		userservice.addNew(userRequest);
		return "User Added Successfully";
	}

	// Endpoint to update an existing user
	@PutMapping("/update/{id}")
	public String updateUser(@PathVariable Integer id,@RequestBody @Valid User user) throws UserNotFoundException {
		userservice.update(id,user);
		return "User Updated Successfully";
	}
	
	// Endpoint to delete a user by their ID
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable Integer id) throws UserNotFoundException {
		userservice.delete(id);
		return "User Deleted Successfully";
	}

	//Endpoint to login a user
	@PostMapping("/login")
    public String loginUser(@RequestBody UserCredentialsDto user) throws InvalidCredentialsException, UserNotFoundException {
    	return userservice.loginUser(user);
    }
}
