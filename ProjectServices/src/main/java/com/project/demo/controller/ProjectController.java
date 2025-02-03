package com.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.dto.ProjectRequest;
import com.project.demo.exception.ProjectNotFound;
import com.project.demo.model.Project;
import com.project.demo.service.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
	@Autowired
	private ProjectService projectservice;

	@GetMapping("/getAll")
	public List<Project> getAllProjects() {
		return projectservice.getAll();
	}
	
	@GetMapping("/getTickets")
	public List<Project> getTickets(){
		return projectservice.getTicketsOfProject();
	}

	@GetMapping("/getOne/{id}")
	public Project getProjectById(@PathVariable Integer id) throws ProjectNotFound {
		return projectservice.getOne(id);
	}

	@PostMapping("/addNew")
	public String addProject(@RequestBody @Valid ProjectRequest projectRequest) {
		projectservice.addNew(projectRequest);
		return "Project Added Successfully";
	}

	@PutMapping("/update/{id}")
	public String updateProject(@PathVariable Integer id, @RequestBody Project project) throws ProjectNotFound {
		projectservice.Update(id,project);
		return "Project Updated Successfully";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteProject(@PathVariable Integer id) throws ProjectNotFound {
		projectservice.delete(id);
		return "Project Deleted Successfully";
	}

	@GetMapping("/getProjectsByUserId/{userId}")
	public List<Project> getProjectsByUserId(@PathVariable("userId") Integer userId) {
		return projectservice.getProjectsByUserId(userId);
	}

}
