package com.project.demo.service;

import java.util.List;
import java.util.Optional;

import com.project.demo.exception.ProjectNotFound;
import com.project.demo.model.Project;

public interface ProjectService {

	public List<Project> getAll();

	public void Update(Integer Id,Project project) throws ProjectNotFound;

	public void addNew(Project project);

	public Project getOne(Integer id) throws ProjectNotFound;

	public void delete(Integer id) throws ProjectNotFound;

	List<Project> getProjectsByUserId(Integer userId);

}
