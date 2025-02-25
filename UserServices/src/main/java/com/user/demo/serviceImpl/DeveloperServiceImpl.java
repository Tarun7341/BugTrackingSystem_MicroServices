package com.user.demo.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.demo.client.ProjectFeignClient;
import com.user.demo.client.TicketFeignClient;
import com.user.demo.dto.Project;
import com.user.demo.dto.Ticket;
import com.user.demo.service.DeveloperService;

@Service
public class DeveloperServiceImpl implements DeveloperService{
	
	private final TicketFeignClient ticketFeignClient;
	
	private final ProjectFeignClient projectFeignClient;

    public DeveloperServiceImpl(TicketFeignClient ticketFeignClient, ProjectFeignClient projectFeignClient) {
        this.ticketFeignClient = ticketFeignClient;
        this.projectFeignClient= projectFeignClient;
    }

    @Override
    public List<Ticket> getTicketsByUser(Integer userId) {
        return ticketFeignClient.getTicketsByUser(userId);
    }

    @Override
    public void updateTicket(Integer id, Ticket ticket) {
        ticketFeignClient.updateTicket(id, ticket);
    }
    
    public List<Project> getProjectsByUser(Integer userId){
    	return projectFeignClient.getProjectsOfUsers(userId);
    	
    }

}
