package com.project.demo.service;

import java.util.List;

import com.project.demo.dto.ProjectRequest;
import com.project.demo.entity.Project;
import com.project.demo.exception.ProjectNotFoundException;

public interface ProjectService {

	public List<Project> getAll();

	public void Update(Integer Id,Project project) throws ProjectNotFoundException;

	public void addNew(ProjectRequest projectRequest);

	public Project getOne(Integer id) throws ProjectNotFoundException;

	public void delete(Integer id) throws ProjectNotFoundException;

	List<Project> getProjectsByUserId(Integer userId);
	
	public List<Project> getTicketsOfProject();

}
