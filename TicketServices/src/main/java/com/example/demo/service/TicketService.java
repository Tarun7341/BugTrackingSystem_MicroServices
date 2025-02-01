package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Ticket;

public interface TicketService {

	public List<Ticket> getAll();

	public void update(Ticket ticket);

	public void addNew(Ticket ticket);

	public Optional<Ticket> getOne(Integer id);

	public void delete(Integer id);

	public List<Ticket> getTicketsByProject(Integer Id);

	public List<Ticket> getTicketsByUser(Integer Id);

}
