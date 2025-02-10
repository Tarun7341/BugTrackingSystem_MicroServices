package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.user.demo.client.ProjectClient;
import com.user.demo.client.TicketClient;
import com.user.demo.dto.UserRequest;
import com.user.demo.exception.UserNotFound;
import com.user.demo.model.Project;
import com.user.demo.model.Ticket;
import com.user.demo.model.User;
import com.user.demo.repository.UserRepository;
import com.user.demo.serviceImpl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BugTrackerSpringRestApplicationTests {

	@Mock
	private UserRepository userRepository;

	@Mock
	private TicketClient ticketClient;

	@Mock
	private ProjectClient projectClient;

	@InjectMocks
	private UserServiceImpl userService;

	private User user;

	@BeforeEach
	public void setUp() throws Exception {
		user = createUser(1, "John", "Doe", "john.doe@example.com", "1234567890", "USER", "password123", null, null);
	}

	private User createUser(Integer id, String firstname, String lastname, String email, String phoneNumber,
			String role, String password, List<Ticket> tickets, List<Project> projects) throws Exception {
		User user = User.class.getDeclaredConstructor().newInstance();
		Field idField = User.class.getDeclaredField("id");
		idField.setAccessible(true);
		idField.set(user, id);
		Field firstnameField = User.class.getDeclaredField("firstname");
		firstnameField.setAccessible(true);
		firstnameField.set(user, firstname);
		Field lastnameField = User.class.getDeclaredField("lastname");
		lastnameField.setAccessible(true);
		lastnameField.set(user, lastname);
		Field emailField = User.class.getDeclaredField("email");
		emailField.setAccessible(true);
		emailField.set(user, email);
		Field phoneNumberField = User.class.getDeclaredField("phoneNumber");
		phoneNumberField.setAccessible(true);
		phoneNumberField.set(user, phoneNumber);
		Field roleField = User.class.getDeclaredField("role");
		roleField.setAccessible(true);
		roleField.set(user, role);
		Field passwordField = User.class.getDeclaredField("password");
		passwordField.setAccessible(true);
		passwordField.set(user, password);
		Field ticketsField = User.class.getDeclaredField("tickets");
		ticketsField.setAccessible(true);
		ticketsField.set(user, tickets);
		Field projectsField = User.class.getDeclaredField("projects");
		projectsField.setAccessible(true);
		projectsField.set(user, projects);
		return user;
	}

	@Test
	public void testGetAll() {
		when(userRepository.findAll()).thenReturn(Arrays.asList(user));

		List<User> userList = userService.getAll();

		assertEquals(1, userList.size());
		assertEquals(user.getEmail(), userList.get(0).getEmail());
	}

	@Test
	public void testGetOne() throws UserNotFound {
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

		User foundUser = userService.getOne(user.getId());

		assertEquals(user.getEmail(), foundUser.getEmail());
	}

	@Test
	public void testAddNew() {
		UserRequest userRequest = new UserRequest(1, "John", "Doe", "john.doe@example.com", "1234567890", "USER",
				"password123");

		userService.addNew(userRequest);

		verify(userRepository, times(1)).save(any(User.class));
	}

	@Test
	public void testUpdate() throws Exception {
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

		User updatedUser = createUser(1, "John", "Smith", "john.smith@example.com", "0987654321", "ADMIN",
				"newpassword123", null, null);
		userService.update(user.getId(), updatedUser);

		assertEquals("Smith", user.getLastname());
		assertEquals("john.smith@example.com", user.getEmail());
		assertEquals("0987654321", user.getPhoneNumber());
		assertEquals("ADMIN", user.getRole());
		assertEquals("newpassword123", user.getPassword());
	}

	@Test
	public void testDelete() throws UserNotFound {
		when(userRepository.existsById(user.getId())).thenReturn(true);

		userService.delete(user.getId());

		verify(userRepository, times(1)).deleteById(user.getId());
	}

	@Test
	public void testGetUserTickets() {
		when(userRepository.findAll()).thenReturn(Arrays.asList(user));
		when(ticketClient.getTicketsOfUsers(user.getId())).thenReturn(Arrays.asList(new Ticket()));

		List<User> userList = userService.getUserTickets();

		assertEquals(1, userList.size());
		assertEquals(1, userList.get(0).getTickets().size());
	}

	@Test
	public void testGetUserProjects() {
		when(userRepository.findAll()).thenReturn(Arrays.asList(user));
		when(projectClient.getProjectsOfUsers(user.getId())).thenReturn(Arrays.asList(new Project()));

		List<User> userList = userService.getUserProjects();

		assertEquals(1, userList.size());
		assertEquals(1, userList.get(0).getProjects().size());
	}
}
