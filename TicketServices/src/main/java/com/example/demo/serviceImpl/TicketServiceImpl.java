package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketrepository;

	@Override
	public List<Ticket> getAll() {
		return (List<Ticket>) ticketrepository.findAll();
	}

	@Override
	public void update(Ticket ticket) {
		ticketrepository.save(ticket);

	}

	@Override
	public void addNew(Ticket ticket) {

		ticketrepository.save(ticket);

	}

	@Override
	public Optional<Ticket> getOne(Integer id) {

		return ticketrepository.findById(id);
	}

	@Override
	public void delete(Integer id) {

		ticketrepository.deleteById(id);
	}

	public List<Ticket> filterTickets(String status, String severity) {
		return ticketrepository.findByStatusAndSeverity(status, severity);
	}

	@Override
	public List<Ticket> getTicketsByProject(Integer Id) {
		return ticketrepository.findByProjectId(Id);
	}

	@Override
	public List<Ticket> getTicketsByUser(Integer Id) {
		return ticketrepository.findByUserId(Id);
	}

}
