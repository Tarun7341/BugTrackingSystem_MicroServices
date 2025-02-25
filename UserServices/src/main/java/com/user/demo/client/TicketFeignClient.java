package com.user.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.demo.dto.Ticket;
//@FeignClient(url = "http://localhost:9092", value = "Ticket-Client")
@FeignClient(name="TICKET-SERVICE")
public interface TicketFeignClient {

	// Endpoint to get tickets of a specific user by user ID
	@GetMapping("/api/tickets/users/{id}")
	List<Ticket> getTicketsOfUsers(@PathVariable Integer id);
	
	  @GetMapping("/api/tickets/getAll")
	    List<Ticket> getAllTickets();

	    @GetMapping("/api/tickets/getOne/{id}")
	    Ticket getTicket(@PathVariable Integer id);

	    @PostMapping("/api/tickets/addNew")
	    void createTicket(@RequestBody Ticket ticket);

	    @PutMapping("/api/tickets/update/{id}")
	    void updateTicket(@PathVariable Integer id, @RequestBody Ticket ticket);

	    @DeleteMapping("/api/tickets/delete/{id}")
	    void deleteTicket(@PathVariable Integer id);

	    @GetMapping("/api/tickets/users/{id}")
	    List<Ticket> getTicketsByUser(@PathVariable Integer id);
	    
	    @PutMapping("/api/tickets/removeUser/{id}/{userIdToRemove}")
		public String removeUserId(@PathVariable Integer id,@PathVariable Integer userIdToRemove);

}
