package com.ticket.demo.controller;

import java.util.List;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.demo.dto.TicketRequest;
import com.ticket.demo.entity.Ticket;

import com.ticket.demo.exception.TicketNotFoundException;
import com.ticket.demo.service.TicketService;

import jakarta.validation.Valid;

/*
 * Author: THARUN A
 * Description: Contains the controller end-points for Ticket Services
 * Actions: Add, Update, Delete, List tickets
 */


@RestController
@RequestMapping("/api/tickets")
public class TicketController {
	// Injecting the TicketService dependency
	private TicketService ticketservice;

	// Constructor-based dependency injection
	public TicketController(TicketService ticketservice) {
		super();
		this.ticketservice = ticketservice;
	}

	// Endpoint to get all tickets
	@GetMapping("/getAll")
	public List<Ticket> getAllTickets() {
		return ticketservice.getAll();
	}
	
	// Endpoint to get a single ticket by its ID
	@GetMapping("/getOne/{id}")
	public Ticket getOne(@PathVariable Integer id) throws TicketNotFoundException {
		return ticketservice.getOne(id);
	}

	// Endpoint to add a new ticket
	@PostMapping("/addNew")
	public String addNewTicket(@RequestBody @Valid TicketRequest ticketRequest) {
		ticketservice.addNew(ticketRequest);
		return "Ticket Added Successfully";
	}
	

	// Endpoint to update an existing ticket
	@PutMapping("/update/{id}")
	public String updateTicket(@PathVariable Integer id,@RequestBody Ticket ticket) throws TicketNotFoundException {
		ticketservice.update(id,ticket);
		return "Ticket Updated Successfully";
	}

	// Endpoint to delete a ticket by its ID
	@DeleteMapping("/delete/{id}")
	public String deleteTicket(@PathVariable Integer id) throws TicketNotFoundException {
		ticketservice.delete(id);
		return "Ticket Deleted Successfully";
	}

	// Endpoint to get tickets by project ID
	@GetMapping("/projects/{id}")
	public List<Ticket> getTicketsByProject(@PathVariable Integer id) {
		return ticketservice.getTicketsByProject(id);

	}
	
	// Endpoint to get tickets by user ID
	@GetMapping("/users/{id}")
	public List<Ticket> getTicketByUser(@PathVariable Integer id) {
		return ticketservice.getTicketsByUser(id);
	}
	
	@PutMapping("/removeUser/{id}/{userIdToRemove}")
	public String removeUserId(@PathVariable Integer id,@PathVariable Integer userIdToRemove) throws TicketNotFoundException {
		return ticketservice.removeUserId(id, userIdToRemove);
	
	}

}
