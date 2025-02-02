package com.ticket.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.demo.dto.TicketRequest;
import com.ticket.demo.exception.TicketNotFound;
import com.ticket.demo.model.Ticket;
import com.ticket.demo.service.TicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
	@Autowired
	private TicketService ticketservice;

	@GetMapping("/getAll")
	public List<Ticket> getAllTickets() {
		return ticketservice.getAll();
	}

	@GetMapping("/getOne")
	public Ticket getOne(@PathVariable Integer id) throws TicketNotFound {
		return ticketservice.getOne(id);
	}

	@PostMapping("/addNew")
	public String addNewTicket(@RequestBody @Valid TicketRequest ticketRequest) {
		ticketservice.addNew(ticketRequest);
		return "Ticket Added Successfully";
	}

	@PutMapping("/update/{id}")
	public String updateTicket(@PathVariable("id") Integer id,@RequestBody Ticket ticket) throws TicketNotFound {
		ticketservice.update(id,ticket);
		return "Ticket Updated Successfully";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteTicket(@PathVariable Integer id) throws TicketNotFound {
		ticketservice.delete(id);
		return "Ticket Deleted Successfully";
	}

	@GetMapping("/projects/{Id}")
	public List<Ticket> getTicketsByProject(@PathVariable Integer Id) {
		return ticketservice.getTicketsByProject(Id);

	}

	@GetMapping("/users/{Id}")
	public List<Ticket> getTicketByUser(@PathVariable Integer Id) {
		return ticketservice.getTicketsByUser(Id);
	}

}
