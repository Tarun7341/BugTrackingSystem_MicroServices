package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.ticket.demo.BugTrackerSpringRestApplication;
import com.ticket.demo.dto.TicketRequest;
import com.ticket.demo.entity.Ticket;
import com.ticket.demo.exception.ResourceNotFoundException;
import com.ticket.demo.exception.TicketNotFoundException;
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
	private TicketRequest ticketRequest;

	@BeforeEach
	public void setup() {
		ticket = Ticket.build(1, "Login Issue", "Users are unable to log in using their credentials.", "Open", "High",
				"Bug", "Critical",
				"1. Go to login page\n2. Enter valid credentials\n3. Click on login button\n4. Observe the error message",
				1, 2);

		ticketRequest = new TicketRequest(1, "Login Issue", "Users are unable to log in using their credentials.",
				"Open", "High", "Bug", "Critical",
				"1. Go to login page\n2. Enter valid credentials\n3. Click on login button\n4. Observe the error message",
				1, 2);
	}

	@Test
	public void testGetAll() {
		List<Ticket> tickets = new ArrayList<>();
		tickets.add(ticket);

		when(ticketRepository.findAll()).thenReturn(tickets);

		List<Ticket> result = ticketService.getAll();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(ticket, result.get(0));

		verify(ticketRepository, times(1)).findAll();
	}

	@Test
	public void testGetOne_Success() throws TicketNotFoundException {
		when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));

		Ticket result = ticketService.getOne(1);

		assertNotNull(result);
		assertEquals(1, result.getId());
		assertEquals("Login Issue", result.getTitle());
		assertEquals("Users are unable to log in using their credentials.", result.getDescription());

		verify(ticketRepository, times(1)).findById(1);
	}

	@Test
	public void testGetOne_TicketNotFound() {
		when(ticketRepository.findById(1)).thenReturn(Optional.empty());

		Exception exception = assertThrows(TicketNotFoundException.class, () -> {
			ticketService.getOne(1);
		});

		assertEquals("Ticket with ID 1 not found !!", exception.getMessage());

		verify(ticketRepository, times(1)).findById(1);
	}

	@Test
	public void testAddNew()  {
		ticketService.addNew(ticketRequest);

		verify(ticketRepository, times(1)).save(any(Ticket.class));
	}

	@Test
	public void testUpdate_Success() throws TicketNotFoundException {
		when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));

		ticketService.update(1, ticket);

		verify(ticketRepository, times(1)).findById(1);
		verify(ticketRepository, times(1)).save(ticket);
	}

	@Test
	public void testDelete_Success() throws TicketNotFoundException {
		when(ticketRepository.existsById(1)).thenReturn(true);

		ticketService.delete(1);

		verify(ticketRepository, times(1)).existsById(1);
		verify(ticketRepository, times(1)).deleteById(1);
	}

	@Test
	public void testDelete_TicketNotFound() {
		when(ticketRepository.existsById(1)).thenReturn(false);

		TicketNotFoundException exception = assertThrows(TicketNotFoundException.class, () -> {
			ticketService.delete(1);
		});

		assertEquals("Ticket with ID 1 not found !!", exception.getMessage());

		verify(ticketRepository, times(1)).existsById(1);
		verify(ticketRepository, times(0)).deleteById(1);
	}

	@Test
	public void testGetTicketsByProject() {
		List<Ticket> tickets = new ArrayList<>();
		tickets.add(ticket);

		when(ticketRepository.findByProjectId(1)).thenReturn(tickets);

		List<Ticket> result = ticketService.getTicketsByProject(1);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(ticket, result.get(0));

		verify(ticketRepository, times(1)).findByProjectId(1);
	}

	@Test
	public void testGetTicketsByUser() {
		List<Ticket> tickets = new ArrayList<>();
		tickets.add(ticket);

		when(ticketRepository.findByUserId(1)).thenReturn(tickets);

		List<Ticket> result = ticketService.getTicketsByUser(1);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(ticket, result.get(0));

		verify(ticketRepository, times(1)).findByUserId(1);
	}
}
