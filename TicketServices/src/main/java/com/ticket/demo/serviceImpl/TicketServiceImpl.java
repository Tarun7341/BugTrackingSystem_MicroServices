package com.ticket.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.demo.dto.TicketRequest;
import com.ticket.demo.exception.TicketNotFound;
import com.ticket.demo.model.Project;
import com.ticket.demo.model.Ticket;
import com.ticket.demo.model.User;
import com.ticket.demo.repository.TicketRepository;
import com.ticket.demo.service.TicketService;

import feign.FeignException;

/*
 * Author: THARUN A
 * Description: Contains the business implementation for Ticket Services
 * Actions: Add, Update, Delete, List tickets
 */

@Service
public class TicketServiceImpl implements TicketService {

	// Injecting the TicketRepository dependency
	@Autowired
	private TicketRepository ticketrepository;

	// Method to retrieve all tickets
	@Override
	public List<Ticket> getAll() {
		return (List<Ticket>) ticketrepository.findAll();
	}

	// Method to update an existing ticket
	@Override
	public void update(Integer id, Ticket updateTicket) throws TicketNotFound {
		Ticket existingTicket = ticketrepository.findById(id).orElseThrow(null);
		if (existingTicket != null) {

			if (updateTicket.getDescription() != null) {
				existingTicket.setDescription(updateTicket.getDescription());
			}

			if (updateTicket.getPriority() != null) {
				existingTicket.setPriority(updateTicket.getPriority());
			}

			if (updateTicket.getProjectId() != null) {
				existingTicket.setProjectId(updateTicket.getProjectId());
			}

			if (updateTicket.getSeverity() != null) {
				existingTicket.setSeverity(updateTicket.getSeverity());
			}

			if (updateTicket.getStatus() != null) {
				existingTicket.setStatus(updateTicket.getStatus());
			}

			if (updateTicket.getStepstoReproduce() != null) {
				existingTicket.setStepstoReproduce(updateTicket.getStepstoReproduce());
			}

			if (updateTicket.getTitle() != null) {
				existingTicket.setTitle(updateTicket.getTitle());
			}

			if (updateTicket.getType() != null) {
				existingTicket.setType(updateTicket.getType());
			}

			if (updateTicket.getUserId() != null) {
				existingTicket.setUserId(updateTicket.getUserId());
			}

			ticketrepository.save(existingTicket);
		} else {
			throw new TicketNotFound("Ticket with ID " + id + " not found !!");
		}

	}

	// Method to add a new ticket
	public void addNew(TicketRequest ticketRequest) {

		Ticket ticket = Ticket.build(ticketRequest.getId(), ticketRequest.getTitle(), ticketRequest.getDescription(),
				ticketRequest.getStatus(), ticketRequest.getPriority(), ticketRequest.getType(),
				ticketRequest.getSeverity(), ticketRequest.getStepstoReproduce(), ticketRequest.getProjectId(),
				ticketRequest.getUserId());
		ticketrepository.save(ticket);

	}

	// Method to retrieve a single ticket by its ID
	@Override
	public Ticket getOne(Integer id) throws TicketNotFound {

		return ticketrepository.findById(id)
				.orElseThrow(() -> new TicketNotFound("Ticket with ID " + id + " not found !!"));
	}

	// Method to delete a ticket by its ID
	@Override
	public void delete(Integer id) throws TicketNotFound {
		if (ticketrepository.existsById(id)) {
			ticketrepository.deleteById(id);
		} else {
			throw new TicketNotFound("Ticket with ID " + id + " not found !!");
		}

	}

	// Method to filter tickets by status and severity
	public List<Ticket> filterTickets(String status, String severity) {
		return ticketrepository.findByStatusAndSeverity(status, severity);
	}

	// Method to retrieve tickets by project ID
	@Override
	public List<Ticket> getTicketsByProject(Integer Id) {
		return ticketrepository.findByProjectId(Id);
	}

	// Method to retrieve tickets by user ID
	@Override
	public List<Ticket> getTicketsByUser(Integer Id) {
		return ticketrepository.findByUserId(Id);
	}

}
