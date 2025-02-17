package com.user.demo.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.demo.client.ProjectClient;
import com.user.demo.client.TicketClient;
import com.user.demo.dto.UserCredentialsDto;
import com.user.demo.dto.UserRequest;
import com.user.demo.exception.InvalidCredentialsException;
import com.user.demo.exception.UserNotFound;
import com.user.demo.model.User;
import com.user.demo.repository.UserRepository;
import com.user.demo.service.UserService;

/*
 * Author: THARUN A
 * Description: Contains the business implementation for User Services
 * Actions: Add, Update, Delete, List users, List user projects, List user tickets
 */


@Service
public class UserServiceImpl implements UserService {

	// Injecting the UserRepository dependency
	@Autowired
	public UserRepository userrepository;

	// Injecting the TicketClient dependency
	@Autowired
	public TicketClient ticketclient;

	// Injecting the ProjectClient dependency
	@Autowired
	public ProjectClient projectclient;

	// Method to retrieve all users
	public List<User> getAll() {
		return userrepository.findAll();
	}

	// Method to retrieve tickets associated with users
	@Override
	public List<User> getUserTickets() {

		List<User> userlist = userrepository.findAll();

		List<User> newuserlist = userlist.stream().map(ulist -> {
			ulist.setTickets(ticketclient.getTicketsOfUsers(ulist.getId()));
			return ulist;
		}).collect(Collectors.toList());
		return newuserlist;
	}

	// Method to update an existing user
	@Override
	public void update(Integer id, User updateUser) throws UserNotFound {
		User existingUser = userrepository.findById(id).orElse(null);

		if (existingUser != null) {

			if (updateUser.getFirstname() != null) {
				existingUser.setFirstname(updateUser.getFirstname());
			}

			if (updateUser.getLastname() != null) {
				existingUser.setLastname(updateUser.getLastname());
			}

			if (updateUser.getEmail() != null) {
				existingUser.setEmail(updateUser.getEmail());
			}

			if (updateUser.getPhoneNumber() != null) {
				existingUser.setPhoneNumber(updateUser.getPhoneNumber());
			}

			if (updateUser.getRole() != null) {
				existingUser.setRole(updateUser.getRole());
			}
			
			if (updateUser.getPassword() != null) {
				existingUser.setPassword(updateUser.getPassword());
			}
			userrepository.save(existingUser);
		} else {
			throw new UserNotFound("User with ID " + id + " not found !!");
		}

	}

	// Method to add a new user
	@Override
	public void addNew(UserRequest userRequest) {
		User user = User.build(userRequest.getId(), userRequest.getFirstname(), userRequest.getLastname(),
				userRequest.getEmail(), userRequest.getPhoneNumber(), userRequest.getRole(),userRequest.getPassword(), null, null);
		userrepository.save(user);

	}

	// Method to retrieve a single user by their ID
	@Override
	public User getOne(Integer id) throws UserNotFound {

		return userrepository.findById(id)
				.orElseThrow(() -> new UserNotFound("User with ID " + id + " not found !!"));
		
	}

	// Method to delete a user by their ID
	@Override
	public void delete(Integer id) throws UserNotFound {
		if (userrepository.existsById(id)) {
			userrepository.deleteById(id);
		} else {
			throw new UserNotFound("Ticket with ID " + id + " not found !!");
		}

	}

	// Method to retrieve projects associated with users
	@Override
	public List<User> getUserProjects() {

		List<User> userlist = userrepository.findAll();

		List<User> newuserlist = userlist.stream().map(ulist -> {
			ulist.setProjects(projectclient.getProjectsOfUsers(ulist.getId()));
			return ulist;
		}).collect(Collectors.toList());
		return newuserlist;

	}
	
	
	@Override
	public String loginUser(UserCredentialsDto user) throws InvalidCredentialsException, UserNotFound {
	    Optional<User> foundUser = userrepository.findByEmail(user.getEmail());
	    
	    if (!foundUser.isPresent()) {
	        throw new UserNotFound("User Not found!!");
	    }
	    
	    User existingUser = foundUser.get();
	    
	    // Added log to check if password matches
	    if (!Objects.equals(user.getPassword(), existingUser.getPassword())) {
	        throw new InvalidCredentialsException("Password is Incorrect!!");
	    }
	    
	    return "User Logged In Successfully!!";
	}

}
