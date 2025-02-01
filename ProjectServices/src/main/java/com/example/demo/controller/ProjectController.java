package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Project;
import com.example.demo.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
	@Autowired
	private ProjectService projectservice;

	@GetMapping("/getAll")
	public List<Project> getAllProjects() {
		return projectservice.getAll();
	}

	@GetMapping("/getOne/{id}")
	public Project getProjectById(@PathVariable Integer id) {
		return projectservice.getOne(id);
	}

	@PostMapping("/addNew")
	public String addProject(@RequestBody Project project) {
		projectservice.addNew(project);
		return "Project Added Successfully";
	}

	@PutMapping("/update")
	public String updateProject(@RequestBody Project project) {
		projectservice.Update(project);
		return "Project Updated Successfully";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteProject(@PathVariable Integer id) {
		projectservice.delete(id);
		return "Project Deleted Successfully";
	}

	@GetMapping("/getProjectsByUserId/{userId}")
	public List<Project> getProjectsByUserId(@PathVariable("userId") Integer userId) {
		return projectservice.getProjectsByUserId(userId);
	}

}
