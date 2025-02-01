package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;

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
	public Optional<Ticket> getOne(@PathVariable Integer id) {
		return ticketservice.getOne(id);
	}

	@PostMapping("/addNew")
	public String addNewTicket(@RequestBody Ticket ticket) {
		ticketservice.addNew(ticket);
		return "Ticket Added Successfully";
	}

	@PutMapping("/update")
	public String updateTicket(@RequestBody Ticket ticket) {
		ticketservice.update(ticket);
		return "Ticket Updated Successfully";
	}

	@DeleteMapping("/delete")
	public String deleteTicket(@PathVariable Integer id) {
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
