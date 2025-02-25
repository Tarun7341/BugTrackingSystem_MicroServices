package com.project.demo.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.client.TicketClient;
import com.project.demo.client.UserClient;
import com.project.demo.dto.ProjectRequest;
import com.project.demo.dto.User;
import com.project.demo.entity.Project;
import com.project.demo.exception.ProjectNotFoundException;
import com.project.demo.exception.ResourceNotFoundException;
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
//	@Override
//	public void Update(Integer id,Project updateProject) throws ProjectNotFoundException {
//		Project existingProject = projectrepository.findById(id).orElse(null);
//		
//		if(existingProject!=null) {
//			
//			if (updateProject.getUserId() != null) {
//				existingProject.setUserId(updateProject.getUserId());
//			}
//			
//			if(updateProject.getName()!=null) {
//				existingProject.setName(updateProject.getName());
//			}
//			
//			if(updateProject.getDescription()!=null) {
//				existingProject.setDescription(updateProject.getDescription());
//			}
//			projectrepository.save(existingProject);
//			
//		}else {
//			throw new ProjectNotFoundException("Project with ID " + id + " not found !!");
//		}
//		
//	}
	
	
	@Override
	public void Update(Integer id, Project updateProject) throws ProjectNotFoundException {
	    Project existingProject = projectrepository.findById(id).orElse(null);
	    
	    if (existingProject != null) {
	        
	        if (updateProject.getUserId() != null) {
	            List<Integer> existingUserIds = existingProject.getUserId();
	            List<Integer> newUserIds = updateProject.getUserId();
	            
	            // Retain existing user IDs and add new user IDs without duplicates
	            Set<Integer> combinedUserIds = new HashSet<>(existingUserIds);
	            combinedUserIds.addAll(newUserIds);
	            
	            existingProject.setUserId(new ArrayList<>(combinedUserIds));
	        }
	        
	        if (updateProject.getName() != null) {
	            existingProject.setName(updateProject.getName());
	        }
	        
	        if (updateProject.getDescription() != null) {
	            existingProject.setDescription(updateProject.getDescription());
	        }
	        
	        projectrepository.save(existingProject);
	        
	    } else {
	        throw new ProjectNotFoundException("Project with ID " + id + " not found !!");
	    }
	}
	
	
	
	public String removeUserId(Integer id, Integer userIdToRemove) throws ProjectNotFoundException {
	    Project existingProject = projectrepository.findById(id).orElse(null);
	    
	    if (existingProject != null) {
	        
	        if (existingProject.getUserId() != null) {
	            List<Integer> existingUserIds = existingProject.getUserId();
	            
	            // Remove the specific userId from the list if it exists
	            existingUserIds.remove(userIdToRemove);
	            
	            existingProject.setUserId(existingUserIds);
	        }

	        projectrepository.save(existingProject);
	        
	    } else {
	        throw new ProjectNotFoundException("Project with ID " + id + " not found !!");
	    }
	    return "User Removed";
	}


	
	
	

	   // Method to add a new project
//	public void addNew(ProjectRequest projectRequest) {
//		
//		try {
//			User user = userClient.getUserById(projectRequest.getUserId());
//		}
//		catch(FeignException.NotFound e) {
//			throw new ResourceNotFoundException("Cannot find User with Id: "+projectRequest.getUserId());
//		}
//		
//		Project project = Project.build(projectRequest.getId(),projectRequest.getName(), projectRequest.getDescription(), projectRequest.getUserId(),null);
//		projectrepository.save(project);
//
//	}
	
	public void addNew(ProjectRequest projectRequest) {
	    List<Integer> userIds = projectRequest.getUserId();
	    List<User> users = new ArrayList<>();

	    for (Integer userId : userIds) {
	        try {
	            User user = userClient.getUserById(userId);
	            users.add(user);
	        } catch (FeignException.NotFound e) {
	            throw new ResourceNotFoundException("Cannot find User with Id: " + userId);
	        }
	    }

	    Project project = Project.build(
	        projectRequest.getId(),
	        projectRequest.getName(),
	        projectRequest.getDescription(),
	        userIds, // Pass the list of user IDs here
	        null
	    );

	    projectrepository.save(project);
	}


	// Method to retrieve a single project by its ID
	@Override
	public Project getOne(Integer id) throws ProjectNotFoundException {
		Project pjt = projectrepository.findById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Project with ID " + id + " not found !! "));
		pjt.setTickets(ticketclient.getTicketsOfProject(pjt.getId()));
		return pjt;
	}

	// Method to delete a project by its ID
	@Override
	public void delete(Integer id) throws ProjectNotFoundException{
		if(projectrepository.existsById(id)) {
			projectrepository.deleteById(id);
		}
		else {
			throw new ProjectNotFoundException("Project with ID " + id + " not found !!");
		}

	}

	// Method to retrieve projects by user ID
//	@Override
//	public List<Project> getProjectsByUserId(List<Integer> userId) {
//
//		return projectrepository.findProjectsByUserId(userId);
//	}
	
	
	@Override
	public List<Project> getProjectsByUserId(List<Integer> userIds) {
	    List<Project> allProjects = projectrepository.findAll();  // Fetch all projects
	    return allProjects.stream()
	                      .filter(project -> userIds.stream().anyMatch(userId -> project.getUserId().contains(userId)))
	                      .collect(Collectors.toList());
	}





}
