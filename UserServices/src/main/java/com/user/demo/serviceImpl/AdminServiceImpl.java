package com.user.demo.serviceImpl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.user.demo.client.ProjectFeignClient;
import com.user.demo.client.TicketFeignClient;
import com.user.demo.client.UserFeignClient;
import com.user.demo.dto.Project;
import com.user.demo.dto.Ticket;
import com.user.demo.entity.User;
import com.user.demo.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	private final ProjectFeignClient projectFeignClient;
    private final TicketFeignClient ticketFeignClient;
    private final UserFeignClient userFeignClient;
    
    private Project project;
    
    private User user;

    public AdminServiceImpl(ProjectFeignClient projectFeignClient, TicketFeignClient ticketFeignClient, UserFeignClient userFeignClient) {
        this.projectFeignClient = projectFeignClient;
        this.ticketFeignClient = ticketFeignClient;
        this.userFeignClient = userFeignClient;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectFeignClient.getAllProjects();
    }

    @Override
    public void addProject(Project project) {
        projectFeignClient.addProject(project);
    }

    @Override
    public void updateProject(Integer id, Project project) {
        projectFeignClient.updateProject(id, project);
    }

    @Override
    public void deleteProject(Integer id) {
        projectFeignClient.deleteProject(id);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketFeignClient.getAllTickets();
    }

    @Override
    public List<User> getAllUsers() {
        return userFeignClient.getAllUsers();
    }
    
    public String assignDeveloperToProject(Integer projectId, List<Integer> userId) {
        // Fetch the user details
        user = userFeignClient.getUserById(userId);
        project = projectFeignClient.getProjectById(projectId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        
        if (project == null) {
            throw new RuntimeException("Project not found");
        }

        // Assign the user to the project
        project.setUserId(userId);
        projectFeignClient.updateProject(projectId, project);
        return "Project assigned";
        
    }
    
    public String removeDeveloperFromProject(Integer projectId, List<Integer> userId) {
        // Fetch the user details
        user = userFeignClient.getUserById(userId);
        project = projectFeignClient.getProjectById(projectId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        
        if (project == null) {
            throw new RuntimeException("Project not found");
        }

        // Assign the user to the project
        project.setUserId(null);
        projectFeignClient.updateProject(projectId, project);
        
        return "Removed User";
    }
    
    public String assignDeveloperToTicket(Integer ticketId, List<Integer> userId) {
        // Fetch the ticket details
        Ticket ticket = ticketFeignClient.getTicket(ticketId);
       // User user = userFeignClient.getUserById(userId);
        
        if (ticket == null) {
            throw new RuntimeException("Ticket not found");
        }

        // Assign the developer to the ticket
        ticket.setUserId(userId);
        ticketFeignClient.updateTicket(ticketId, ticket);
        return "Assigned User";
    }
    
//    public String removeDeveloperFromTicket(Integer ticketId, List<Integer> userId) {
//        // Fetch the ticket details
//        Ticket ticket = ticketFeignClient.getTicket(ticketId);
//        User user = userFeignClient.getUserById(userId);
//        
//        if (ticket == null) {
//            throw new RuntimeException("Ticket not found");
//        }
//
//        // Assign the developer to the ticket
//        ticket.setUserId(userId);
//        ticketFeignClient.updateTicket(ticketId, ticket);
//        return "Removed User";
//    }
    
    public String removeDeveloperFromTicket(Integer ticketId, Integer userIdsToRemove) {
        // Fetch the ticket details
        Ticket ticket = ticketFeignClient.getTicket(ticketId);

        if (ticket == null) {
            throw new RuntimeException("Ticket not found");
        }

        // Remove the specified user IDs from the ticket's user ID list
        List<Integer> existingUserIds = ticket.getUserId();
        if (existingUserIds != null) {
            existingUserIds.remove(userIdsToRemove);
            ticket.setUserId(existingUserIds); // Update the ticket with the modified list
        }

        // Update the ticket
        ticketFeignClient.updateTicket(ticketId, ticket);
        return "Removed User(s)";
    }
    
    
    public String removeUserId(Integer ticketId, Integer userIdToRemove) {
    	Ticket ticket = ticketFeignClient.getTicket(ticketId);

        if (ticket == null) {
            throw new RuntimeException("Ticket not found");
        }

        // Remove the specified user IDs from the ticket's user ID list
        List<Integer> existingUserIds = ticket.getUserId();
        if (existingUserIds != null) {
            existingUserIds.remove(userIdToRemove);
            ticket.setUserId(existingUserIds); // Update the ticket with the modified list
        }

        // Update the ticket
        ticketFeignClient.removeUserId(ticketId, userIdToRemove);
        return "Removed User(s)";
    	
    }
    
    
    public String removeUserIdFromProject(Integer projectId, Integer userIdToRemove) {
        Project project = projectFeignClient.getProjectById(projectId);

        if (project == null) {
            throw new RuntimeException("Project not found");
        }

        // Remove the specified user ID from the project's user ID list
        List<Integer> existingUserIds = project.getUserId();
        if (existingUserIds != null) {
            // Create a new list to avoid modifying the original list
            existingUserIds.remove(userIdToRemove);
            project.setUserId(existingUserIds); // Update the project with the modified list
        }

        // Save the updated project
        projectFeignClient.removeUserId(projectId, userIdToRemove);
        return "Removed User(s)";
    }


    
    

    
    



    
}
