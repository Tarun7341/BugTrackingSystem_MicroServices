package com.project.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.client.TicketClient;
import com.project.demo.client.UserClient;
import com.project.demo.dto.ProjectRequest;
import com.project.demo.exception.ProjectNotFound;
import com.project.demo.exception.ResourceNotFound;
import com.project.demo.model.Project;
import com.project.demo.model.User;
import com.project.demo.repository.ProjectRepository;
import com.project.demo.service.ProjectService;

import feign.FeignException;

/*
 * Author: THARUN A
 * Description: Contains the business implementation for Project Services
 * Actions: Add, Update, Delete, List projects, List project tickets
 */


@Service
public class ProjectServiceImpl implements ProjectService {

    // Injecting the ProjectRepository dependency
	@Autowired
	private ProjectRepository projectrepository;

	// Injecting the TicketClient dependency
	@Autowired
	TicketClient ticketclient;
	
	@Autowired
	UserClient userClient;
	
	// Method to retrieve all projects
	@Override
	public List<Project> getAll() {
		return projectrepository.findAll();
	}
	
	 // Method to retrieve projects and their associated tickets
	public List<Project> getTicketsOfProject() {
		List<Project> pjlist = (List<Project>) projectrepository.findAll();

		List<Project> newpjlist = pjlist.stream().map(pjt -> {
			pjt.setTickets(ticketclient.getTicketsOfProject(pjt.getId()));
			return pjt;
		}).collect(Collectors.toList());
		return newpjlist;
	}

	// Method to update an existing project
	@Override
	public void Update(Integer id,Project updateProject) throws ProjectNotFound {
		Project existingProject = projectrepository.findById(id).orElse(null);
		
		if(existingProject!=null) {
			
			if (updateProject.getUserId() != null) {
				existingProject.setUserId(updateProject.getUserId());
			}
			
			if(updateProject.getName()!=null) {
				existingProject.setName(updateProject.getName());
			}
			
			if(updateProject.getDescription()!=null) {
				existingProject.setDescription(updateProject.getDescription());
			}
			projectrepository.save(existingProject);
			
		}else {
			throw new ProjectNotFound("Project with ID " + id + " not found !!");
		}
		
	}

	   // Method to add a new project
	public void addNew(ProjectRequest projectRequest) {
		
		try {
			User user = userClient.getUserById(projectRequest.getUserId());
		}
		catch(FeignException.NotFound e) {
			throw new ResourceNotFound("Cannot find User with Id: "+projectRequest.getUserId());
		}
		
		Project project = Project.build(projectRequest.getId(),projectRequest.getName(), projectRequest.getDescription(), projectRequest.getUserId(),null);
		projectrepository.save(project);

	}

	// Method to retrieve a single project by its ID
	@Override
	public Project getOne(Integer id) throws ProjectNotFound {
		Project pjt = projectrepository.findById(id)
				.orElseThrow(() -> new ProjectNotFound("Project with ID " + id + " not found !! "));
		pjt.setTickets(ticketclient.getTicketsOfProject(pjt.getId()));
		return pjt;
	}

	// Method to delete a project by its ID
	@Override
	public void delete(Integer id) throws ProjectNotFound{
		if(projectrepository.existsById(id)) {
			projectrepository.deleteById(id);
		}
		else {
			throw new ProjectNotFound("Project with ID " + id + " not found !!");
		}

	}

	// Method to retrieve projects by user ID
	@Override
	public List<Project> getProjectsByUserId(Integer userId) {

		return projectrepository.findProjectsByUserId(userId);
	}




}
