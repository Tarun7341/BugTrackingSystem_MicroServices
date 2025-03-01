package com.ticket.demo.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.demo.client.ProjectClient;
import com.ticket.demo.client.UserClient;
import com.ticket.demo.dto.Project;
import com.ticket.demo.dto.TicketRequest;
import com.ticket.demo.dto.User;
import com.ticket.demo.entity.Ticket;
import com.ticket.demo.exception.ResourceNotFoundException;
import com.ticket.demo.exception.TicketNotFoundException;
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
	
	@Autowired
	private UserClient userClient;
	
	@Autowired
	private ProjectClient projectClient;

	// Method to retrieve all tickets
	@Override
	public List<Ticket> getAll() {
		return (List<Ticket>) ticketrepository.findAll();
	}

	// Method to update an existing ticket
//	@Override
//	public void update(Integer id, Ticket updateTicket) throws TicketNotFoundException {
//		Ticket existingTicket = ticketrepository.findById(id).orElseThrow(()-> new TicketNotFoundException("Ticket with ID " + id + " not found !!"));
//		if (existingTicket != null) {
//
//			if (updateTicket.getDescription() != null) {
//				existingTicket.setDescription(updateTicket.getDescription());
//			}
//
//			if (updateTicket.getPriority() != null) {
//				existingTicket.setPriority(updateTicket.getPriority());
//			}
//
//			if (updateTicket.getProjectId() != null) {
//				existingTicket.setProjectId(updateTicket.getProjectId());
//			}
//
//			if (updateTicket.getSeverity() != null) {
//				existingTicket.setSeverity(updateTicket.getSeverity());
//			}
//
//			if (updateTicket.getStatus() != null) {
//				existingTicket.setStatus(updateTicket.getStatus());
//			}
//
//			if (updateTicket.getStepstoReproduce() != null) {
//				existingTicket.setStepstoReproduce(updateTicket.getStepstoReproduce());
//			}
//
//			if (updateTicket.getTitle() != null) {
//				existingTicket.setTitle(updateTicket.getTitle());
//			}
//
//			if (updateTicket.getType() != null) {
//				existingTicket.setType(updateTicket.getType());
//			}
//
//			if (updateTicket.getUserId() != null) {
//				existingTicket.setUserId(updateTicket.getUserId());
//			}
//
//			ticketrepository.save(existingTicket);
//		}
//
//	}
	
	
	@Override
	public void update(Integer id, Ticket updateTicket) throws TicketNotFoundException {
	    Ticket existingTicket = ticketrepository.findById(id).orElse(null);
	    
	    if (existingTicket != null) {
	        
	        if (updateTicket.getUserId() != null) {
	            List<Integer> existingUserIds = existingTicket.getUserId();
	            List<Integer> newUserIds = updateTicket.getUserId();
	            
	            // Retain existing user IDs and add new user IDs without duplicates
	            Set<Integer> combinedUserIds = new HashSet<>(existingUserIds);
	            combinedUserIds.addAll(newUserIds);
	            
	            existingTicket.setUserId(new ArrayList<>(combinedUserIds));
	        }
	        
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

	        ticketrepository.save(existingTicket);
	        
	    } else {
	        throw new TicketNotFoundException("Ticket with ID " + id + " not found !!");
	    }
	}
	
	
	@Override
	public String removeUserId(Integer id, Integer userIdToRemove) throws TicketNotFoundException {
	    Ticket existingTicket = ticketrepository.findById(id).orElse(null);
	    
	    if (existingTicket != null) {
	        
	        if (existingTicket.getUserId() != null) {
	            List<Integer> existingUserIds = existingTicket.getUserId();
	            
	            // Remove the specific userId from the list if it exists
	            existingUserIds.remove(userIdToRemove);
	            
	            existingTicket.setUserId(existingUserIds);
	        }

	        ticketrepository.save(existingTicket);
	        
	    } else {
	        throw new TicketNotFoundException("Ticket with ID " + id + " not found !!");
	    }
	    return "User Removed";
	}




	// Method to add a new ticket
//	public void addNew(TicketRequest ticketRequest) {
//
//
//		try {
//        User user = userClient.getUserById(ticketRequest.getUserId());
//		}
//        catch (FeignException.NotFound e) {
//            throw new ResourceNotFoundException("User does not exist");
//        }
//
//        // Check if project exists
//		try {
//        Project project = projectClient.getProjectById(ticketRequest.getProjectId());
//		}
//        catch (FeignException.NotFound e) {
//            throw new ResourceNotFoundException("Project does not exist");
//        
//        }
//		
//		Ticket ticket = Ticket.build(ticketRequest.getId(), ticketRequest.getTitle(), ticketRequest.getDescription(),
//				ticketRequest.getStatus(), ticketRequest.getPriority(), ticketRequest.getType(),
//				ticketRequest.getSeverity(), ticketRequest.getStepstoReproduce(), ticketRequest.getProjectId(),
//				ticketRequest.getUserId());
//		ticketrepository.save(ticket);
//
//	}
	
	
	public void addNew(Ticket ticket) {
//	    List<Integer> userIds = ticketRequest.getUserId();
//	    List<User> users = new ArrayList<>();
//
//	    // Validate each user ID in the list
//	    for (Integer userId : userIds) {
//	        try {
//	            User user = userClient.getUserById(userId);
//	            users.add(user);
//	        } catch (FeignException.NotFound e) {
//	            throw new ResourceNotFoundException("User does not exist with Id: " + userId);
//	        }
//	    }
//
//	    // Check if project exists
//	    try {
//	        Project project = projectClient.getProjectById(ticketRequest.getProjectId());
//	    } catch (FeignException.NotFound e) {
//	        throw new ResourceNotFoundException("Project does not exist with Id: " + ticketRequest.getProjectId());
//	    }
//
//	    // Create the Ticket object and set its attributes
//	    Ticket ticket = Ticket.build(
//	        ticketRequest.getId(),
//	        ticketRequest.getTitle(),
//	        ticketRequest.getDescription(),
//	        ticketRequest.getStatus(),
//	        ticketRequest.getPriority(),
//	        ticketRequest.getType(),
//	        ticketRequest.getSeverity(),
//	        ticketRequest.getStepstoReproduce(),
//	        ticketRequest.getProjectId(),
//	        userIds // Pass the list of user IDs here
//	    );

	    // Save the Ticket object to the repository
	    ticketrepository.save(ticket);
	}

	
	

	// Method to retrieve a single ticket by its ID
	@Override
	public Ticket getOne(Integer id) throws TicketNotFoundException {

		return ticketrepository.findById(id)
				.orElseThrow(() -> new TicketNotFoundException("Ticket with ID " + id + " not found !!"));
	}

	// Method to delete a ticket by its ID
	@Override
	public void delete(Integer id) throws TicketNotFoundException {
		if (ticketrepository.existsById(id)) {
			ticketrepository.deleteById(id);
		} else {
			throw new TicketNotFoundException("Ticket with ID " + id + " not found !!");
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
//	@Override
//	public List<Ticket> getTicketsByUser(Integer userIds) {
//	    return ticketrepository.findByUserId(userIds);
//	}
	
	
	@Override
	public List<Ticket> getTicketsByUser(Integer userId) {
	    List<Ticket> allTickets = ticketrepository.findAll();  // Fetch all tickets
	    return allTickets.stream()
	                     .filter(ticket -> ticket.getUserId().contains(userId))
	                     .collect(Collectors.toList());
	}


}
