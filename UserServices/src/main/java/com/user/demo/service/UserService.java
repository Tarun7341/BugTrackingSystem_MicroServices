package com.user.demo.service;

import java.util.List;
import java.util.Optional;

import com.user.demo.exception.UserNotFound;
import com.user.demo.model.User;

public interface UserService {

	public List<User> getAll();

	public void update(Integer id,User user) throws UserNotFound;

	public void addNew(User user);

	public User getOne(Integer id) throws UserNotFound;

	public void delete(Integer id)throws UserNotFound;
}
