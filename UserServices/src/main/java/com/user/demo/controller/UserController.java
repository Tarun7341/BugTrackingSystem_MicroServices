package com.user.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.demo.dto.UserRequest;
import com.user.demo.exception.UserNotFound;
import com.user.demo.model.User;
import com.user.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userservice;

	@GetMapping("/getAll")
	public List<User> getAllUsers() {
		return userservice.getAll();
	}

	@GetMapping("/getOne/{id}")
	public User getOneUser(@PathVariable Integer id) throws UserNotFound {
		return userservice.getOne(id);
	}

	@PostMapping("/addNew")
	public String addNewUser(@RequestBody @Valid UserRequest userRequest) {
		userservice.addNew(userRequest);
		return "User Added Successfully";
	}

	@PutMapping("/update/{id}")
	public String updateUser(@PathVariable Integer id,@RequestBody User user) throws UserNotFound {
		userservice.update(id,user);
		return "User Updated Successfully";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable Integer id) throws UserNotFound {
		userservice.delete(id);
		return "User Deleted Successfully";
	}

}
