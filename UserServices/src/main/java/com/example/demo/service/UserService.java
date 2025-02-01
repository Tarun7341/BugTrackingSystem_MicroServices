package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.User;

public interface UserService {

	public List<User> getAll();

	public void update(User user);

	public void addNew(User user);

	public User getOne(Integer id);

	public boolean delete(Integer id);
}
