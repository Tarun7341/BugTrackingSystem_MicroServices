package com.user.demo.service;

import java.util.List;

import com.user.demo.dto.Project;
import com.user.demo.dto.Ticket;
import com.user.demo.entity.User;

public interface AdminService {
	
	List<Project> getAllProjects();

    void addProject(Project project);

    void updateProject(Integer id, Project project);

    void deleteProject(Integer id);

    List<Ticket> getAllTickets();

    List<User> getAllUsers();

}
