package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TicketClient;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userrepository;

	@Autowired
	public TicketClient ticketclient;

	@Override
	public List<User> getAll() {

		List<User> userlist = userrepository.findAll();

		List<User> newuserlist = userlist.stream().map(ulist -> {
			ulist.setTickets(ticketclient.getTicketsOfUsers(ulist.getId()));
			return ulist;
		}).collect(Collectors.toList());
		return newuserlist;
	}

	@Override
	public void update(User user) {
		userrepository.save(user);

	}

	@Override
	public void addNew(User user) {
		userrepository.save(user);

	}

	@Override
	public User getOne(Integer id) {

		User user = userrepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find with ID: " + id));
		user.setTickets(ticketclient.getTicketsOfUsers(user.getId()));
		return user;
	}

	@Override
	public boolean delete(Integer id) {
		if (userrepository.existsById(id)) {
			userrepository.deleteById(id);
			return true;
		}

		return false;

	}

}
