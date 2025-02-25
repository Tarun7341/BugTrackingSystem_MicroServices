package com.user.demo.service;

import java.util.List;

import com.user.demo.dto.Project;
import com.user.demo.dto.Ticket;

public interface DeveloperService {
    
    List<Ticket> getTicketsByUser(Integer userId);
    
    void updateTicket(Integer id, Ticket ticket);
    
    List<Project> getProjectsByUser(Integer userId);
}
