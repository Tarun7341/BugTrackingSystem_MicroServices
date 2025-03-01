package com.project.demo.service;

import java.util.List;

import com.project.demo.dto.ProjectIdNameProjection;
import com.project.demo.dto.UserDTO;
import com.project.demo.entity.Project;
import com.project.demo.exception.ProjectNotFoundException;

public interface ProjectService {

	public List<Project> getAll();

	public void Update(Integer Id,Project project) throws ProjectNotFoundException;

	public void addNew(Project project);

	public Project getOne(Integer id) throws ProjectNotFoundException;

	public void delete(Integer id) throws ProjectNotFoundException;

	List<Project> getProjectsByUserId(List<Integer> userId);
	
	public List<Project> getTicketsOfProject();
	
	public String removeUserId(Integer id, Integer userIdToRemove) throws ProjectNotFoundException;
	
	public List<ProjectIdNameProjection> fetchProjectIdsAndNames();
	
	

}
