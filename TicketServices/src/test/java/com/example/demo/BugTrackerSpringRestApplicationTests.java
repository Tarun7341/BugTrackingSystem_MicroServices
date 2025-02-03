package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.ticket.demo.BugTrackerSpringRestApplication;
import com.ticket.demo.exception.TicketNotFound;
import com.ticket.demo.model.Ticket;
import com.ticket.demo.repository.TicketRepository;
import com.ticket.demo.serviceImpl.TicketServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = BugTrackerSpringRestApplication.class)
class BugTrackerSpringRestApplicationTests {

	@InjectMocks
	private TicketServiceImpl ticketService;

	@Mock
	private TicketRepository ticketRepository;

	private Ticket ticket;

	@BeforeEach
	public void setup() {
		ticket = Ticket.build(1, "Login Issue", "Users are unable to log in using their credentials.", "Open", "High",
				"Bug", "Critical",
				"1. Go to login page\n2. Enter valid credentials\n3. Click on login button\n4. Observe the error message",
				1, 2);
	}

	@Test
	public void testGetOne() throws TicketNotFound {
		// Pass case
		when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));

		Ticket result = ticketService.getOne(1);
		assertEquals("Login Issue", result.getTitle());
		assertEquals("Users are unable to log in using their credentials.", result.getDescription());
	}

	@Test
	public void testGetOneTicketNotFound() {
		// Fail case
		when(ticketRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(TicketNotFound.class, () -> ticketService.getOne(1));
	}

}
