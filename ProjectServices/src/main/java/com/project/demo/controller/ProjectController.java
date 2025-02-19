package com.project.demo.controller;

import java.util.List;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.dto.ProjectRequest;
import com.project.demo.entity.Project;
import com.project.demo.exception.ProjectNotFoundException;
import com.project.demo.service.ProjectService;

import jakarta.validation.Valid;

/*
 * Author: THARUN A
 * Description: Contains the controller end-points for Project Services
 * Actions: Add, Update, Delete, List projects, List project tickets
 */

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
	
	 // Injecting the ProjectService dependency
	private ProjectService projectservice;
	
	
	  // Constructor-based dependency injection
	public ProjectController(ProjectService projectservice) {
		super();
		this.projectservice = projectservice;
	}

	 // Endpoint to get all projects
	@GetMapping("/getAll")
	public List<Project> getAllProjects() {
		return projectservice.getAll();
	}
	
	// Endpoint to get tickets of a project
	@GetMapping("/getTickets")
	public List<Project> getTickets(){
		return projectservice.getTicketsOfProject();
	}

	 // Endpoint to get a project by its ID
	@GetMapping("/getOne/{id}")
	public Project getProjectById(@PathVariable Integer id) throws ProjectNotFoundException {
		return projectservice.getOne(id);
	}

    // Endpoint to add a new project
	@PostMapping("/addNew")
	public String addProject(@RequestBody @Valid ProjectRequest projectRequest) {
		projectservice.addNew(projectRequest);
		return "Project Added Successfully";
	}


    // Endpoint to update an existing project
	@PutMapping("/update/{id}")
	public String updateProject(@PathVariable Integer id, @RequestBody Project project) throws ProjectNotFoundException {
		projectservice.Update(id,project);
		return "Project Updated Successfully";
	}
	
	  // Endpoint to delete a project by its ID
	@DeleteMapping("/delete/{id}")
	public String deleteProject(@PathVariable Integer id) throws ProjectNotFoundException {
		projectservice.delete(id);
		return "Project Deleted Successfully";
	}
	
	
    // Endpoint to get projects by user ID
	@GetMapping("/getProjectsByUserId/{userId}")
	public List<Project> getProjectsByUserId(@PathVariable("userId") Integer userId) {
		return projectservice.getProjectsByUserId(userId);
	}

}
