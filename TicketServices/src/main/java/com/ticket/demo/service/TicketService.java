package com.ticket.demo.service;

import java.util.List;

import com.ticket.demo.dto.TicketRequest;
import com.ticket.demo.exception.ResourceNotFound;
import com.ticket.demo.exception.TicketNotFound;
import com.ticket.demo.model.Ticket;

public interface TicketService {

	public List<Ticket> getAll();

	public void update(Integer id,Ticket ticket) throws TicketNotFound;

	public void addNew(TicketRequest ticketRequest);

	public Ticket getOne(Integer id) throws TicketNotFound;

	public void delete(Integer id) throws TicketNotFound;

	public List<Ticket> getTicketsByProject(Integer Id);

	public List<Ticket> getTicketsByUser(Integer Id);

}
