package com.user.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.demo.dto.Project;
import com.user.demo.dto.Ticket;
import com.user.demo.serviceImpl.DeveloperServiceImpl;

@RestController
@RequestMapping("/api/developer")
public class DeveloperController {

    private final DeveloperServiceImpl developerService;

    public DeveloperController(DeveloperServiceImpl developerService) {
        this.developerService = developerService;
    }

    @GetMapping("/tickets/{id}")
    public List<Ticket> getTicketsByUser(@PathVariable Integer id) {
        return developerService.getTicketsByUser(id);
    }
    
    @GetMapping("/projects/{id}")
    public List<Project> getProjectsByUser(@PathVariable Integer id){
    	return developerService.getProjectsByUser(id);
    }

    @PutMapping("/tickets/update/{id}")
    public String updateTicket(@PathVariable Integer id, @RequestBody Ticket ticket) {
        developerService.updateTicket(id, ticket);
        return "Ticket updated successfully";
    }
}
