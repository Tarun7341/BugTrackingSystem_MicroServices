package com.project.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.demo.model.Ticket;

//@FeignClient(url = "http://localhost:9092", value = "Ticket-Client")
@FeignClient(name="TICKET-SERVICE")
public interface TicketClient {

	// Endpoint to get tickets of a specific project by project ID
	@GetMapping("/api/tickets/projects/{id}")
	List<Ticket> getTicketsOfProject(@PathVariable Integer id);

}
