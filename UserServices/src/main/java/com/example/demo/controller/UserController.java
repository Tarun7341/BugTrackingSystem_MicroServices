package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

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
	public User getOneUser(@PathVariable Integer id) {
		return userservice.getOne(id);
	}

	@PostMapping("/addNew")
	public String addNewUser(@RequestBody User user) {
		userservice.addNew(user);
		return "User Added Successfully";
	}

	@PutMapping("/update")
	public String updateUser(@RequestBody User user) {
		userservice.update(user);
		return "User Updated Successfully";
	}

	@DeleteMapping("/delete")
	public String deleteUser(@PathVariable Integer id) {
		userservice.delete(id);
		return "User Deleted Successfully";
	}

}
