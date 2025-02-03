package com.project.demo.service;

import java.util.List;

import com.project.demo.dto.ProjectRequest;
import com.project.demo.exception.ProjectNotFound;
import com.project.demo.model.Project;

public interface ProjectService {

	public List<Project> getAll();

	public void Update(Integer Id,Project project) throws ProjectNotFound;

	public void addNew(ProjectRequest projectRequest);

	public Project getOne(Integer id) throws ProjectNotFound;

	public void delete(Integer id) throws ProjectNotFound;

	List<Project> getProjectsByUserId(Integer userId);
	
	public List<Project> getTicketsOfProject();

}
