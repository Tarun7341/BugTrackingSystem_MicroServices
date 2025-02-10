package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.demo.BugTrackerSpringRestApplication;
import com.user.demo.client.TicketClient;
import com.user.demo.exception.UserNotFound;
import com.user.demo.model.User;
import com.user.demo.repository.UserRepository;
import com.user.demo.serviceImpl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = BugTrackerSpringRestApplication.class)
class BugTrackerSpringRestApplicationTests {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private TicketClient ticketClient;

	private User user;

	@BeforeEach
	public void setup() {
		user = User.build(1, "John", "Doe", "john.doe@example.com", "1234567890", "Developer","John@123", null, null);
	}

	@Test
	 void testGetOne() throws UserNotFound {
		// Pass case
		when(userRepository.findById(1)).thenReturn(Optional.of(user));
		when(ticketClient.getTicketsOfUsers(1)).thenReturn(new ArrayList<>());

		User result = userService.getOne(1);
		assertEquals("John", result.getFirstname());
		assertEquals("Doe", result.getLastname());
	}

	@Test
	 void testGetOneUserNotFound() {
		// Fail case
		when(userRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(UserNotFound.class, () -> userService.getOne(1));
	}
}
