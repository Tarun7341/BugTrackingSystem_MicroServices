package com.ticket.demo.service;

import java.util.List;

import com.ticket.demo.dto.TicketRequest;
import com.ticket.demo.entity.Ticket;

import com.ticket.demo.exception.TicketNotFoundException;

public interface TicketService {

	public List<Ticket> getAll();

	public void update(Integer id,Ticket ticket) throws TicketNotFoundException;

	public void addNew(TicketRequest ticketRequest);

	public Ticket getOne(Integer id) throws TicketNotFoundException;

	public void delete(Integer id) throws TicketNotFoundException;

	public List<Ticket> getTicketsByProject(Integer Id);

	public List<Ticket> getTicketsByUser(Integer id);
	
	public String removeUserId(Integer id, Integer userIdToRemove) throws TicketNotFoundException;

}
