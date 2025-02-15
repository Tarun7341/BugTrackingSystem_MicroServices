package com.user.demo.service;

import java.util.List;

import com.user.demo.dto.UserCredentialsDto;
import com.user.demo.dto.UserRequest;
import com.user.demo.exception.InvalidCredentialsException;
import com.user.demo.exception.UserNotFound;
import com.user.demo.model.User;

public interface UserService {

	public List<User> getAll();

	public void update(Integer id,User user) throws UserNotFound;

	public void addNew(UserRequest userRequest);

	public User getOne(Integer id) throws UserNotFound;

	public void delete(Integer id)throws UserNotFound;
	
	public List<User> getUserProjects();
	
	public List<User> getUserTickets();
	
	public String loginUser(UserCredentialsDto userCredDto) throws InvalidCredentialsException, UserNotFound;
}
