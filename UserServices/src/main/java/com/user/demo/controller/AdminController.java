package com.user.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.demo.dto.Project;
import com.user.demo.dto.Ticket;
import com.user.demo.entity.User;
import com.user.demo.serviceImpl.AdminServiceImpl;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminServiceImpl adminService;

    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/getProjects")
    public List<Project> getAllProjects() {
        return adminService.getAllProjects();
    }

    @PostMapping("/addProjects")
    public String addProject(@RequestBody Project project) {
        adminService.addProject(project);
        return "Project added successfully";
    }

    @PutMapping("/projects/{id}")
    public String updateProject(@PathVariable Integer id, @RequestBody Project project) {
        adminService.updateProject(id, project);
        return "Project updated successfully";
    }

    @DeleteMapping("/projects/{id}")
    public String deleteProject(@PathVariable Integer id) {
        adminService.deleteProject(id);
        return "Project deleted successfully";
    }

    @GetMapping("/getTickets")
    public List<Ticket> getAllTickets() {
        return adminService.getAllTickets();
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }
    
    @PutMapping("/project/{projectId}/assign/{userId}")
    public String assignDevProject(@PathVariable Integer projectId,@PathVariable List<Integer> userId) {
    	return adminService.assignDeveloperToProject(projectId, userId);
    }
    
    @PutMapping("/project/{projectId}/remove/{userId}")
    public String removeDevProject(@PathVariable Integer projectId,@PathVariable Integer userId) {
    	return adminService.removeUserIdFromProject(projectId, userId);
    }
    
    @PutMapping("/ticket/{ticketId}/assign/{userId}")
    public String assignDeveloperToTicket(@PathVariable Integer ticketId,@PathVariable List<Integer> userId) {
    	return adminService.assignDeveloperToTicket(ticketId, userId);
    }
    
    
    @PutMapping("/ticket/{ticketId}/remove/{userId}")
    public String removeDeveloperFromTicket(@PathVariable Integer ticketId,@PathVariable Integer userId) {
    	return adminService.removeUserId(ticketId, userId);
    }
    
    
}