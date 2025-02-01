package com.user.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.demo.model.Ticket;

@FeignClient(url = "http://localhost:9092", value = "Ticket-Client")
public interface TicketClient {

	@GetMapping("/api/tickets/projects/{Id}")
	List<Ticket> getTicketsOfProject(@PathVariable Integer Id);

	@GetMapping("/api/tickets/users/{Id}")
	List<Ticket> getTicketsOfUsers(@PathVariable Integer Id);

}
