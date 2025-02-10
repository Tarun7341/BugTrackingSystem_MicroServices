package com.user.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.demo.model.Ticket;
//@FeignClient(url = "http://localhost:9092", value = "Ticket-Client")
@FeignClient(name="TICKET-SERVICE")
public interface TicketClient {

	// Endpoint to get tickets of a specific user by user ID
	@GetMapping("/api/tickets/users/{id}")
	List<Ticket> getTicketsOfUsers(@PathVariable Integer id);

}
