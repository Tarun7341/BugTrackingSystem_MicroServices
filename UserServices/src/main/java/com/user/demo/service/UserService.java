package com.user.demo.service;

import java.util.List;

import com.user.demo.dto.UserCredentialsDto;
import com.user.demo.dto.UserRequest;
import com.user.demo.entity.User;
import com.user.demo.exception.InvalidCredentialsException;
import com.user.demo.exception.UserNotFoundException;

public interface UserService {

	public List<User> getAll();

	public void update(Integer id,User user) throws UserNotFoundException;

	public void addNew(UserRequest userRequest);

	public User getOne(Integer id) throws UserNotFoundException;

	public void delete(Integer id)throws UserNotFoundException;
	
	public List<User> getUserProjects();
	
	public List<User> getUserTickets();
	
	public String loginUser(UserCredentialsDto userCredDto) throws InvalidCredentialsException, UserNotFoundException;
}
