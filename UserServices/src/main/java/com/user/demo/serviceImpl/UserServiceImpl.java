package com.user.demo.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.demo.client.TicketClient;
import com.user.demo.exception.UserNotFound;
import com.user.demo.model.User;
import com.user.demo.repository.UserRepository;
import com.user.demo.service.UserService;

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
	public void update(Integer id,User updateUser) throws UserNotFound {
		Optional<User> optionalUser = userrepository.findById(id);
		
		if(optionalUser.isPresent()) {
			User existingUser = optionalUser.get();
			
			if(updateUser.getFirstname()!=null) {
				existingUser.setFirstname(updateUser.getFirstname());
			}
			
			if(updateUser.getLastname()!=null) {
				existingUser.setLastname(updateUser.getLastname());
			}
			
			if(updateUser.getEmail()!=null) {
				existingUser.setEmail(updateUser.getEmail());
			}
			
			if(updateUser.getPhoneNumber()!=null) {
				existingUser.setPhoneNumber(updateUser.getPhoneNumber());
			}
			
			if(updateUser.getRole()!=null) {
				existingUser.setRole(updateUser.getRole());
			}
			userrepository.save(existingUser);
		}
		else {
			throw new UserNotFound("User with ID "+id+" not found !!");
		}
		

	}

	@Override
	public void addNew(User user) {
		userrepository.save(user);

	}

	@Override
	public User getOne(Integer id) throws UserNotFound {

		User user = userrepository.findById(id).orElseThrow(() -> new UserNotFound("User with ID "+id+" not found !!"));
		user.setTickets(ticketclient.getTicketsOfUsers(user.getId()));
		return user;
	}

	@Override
	public void delete(Integer id) throws UserNotFound {
		if (userrepository.existsById(id)) {
			userrepository.deleteById(id);
		}
		else {
			throw new UserNotFound("Ticket with ID "+id+" not found !!");
		}

	}

}
