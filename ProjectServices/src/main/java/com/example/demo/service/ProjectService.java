package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Project;

public interface ProjectService {

	public List<Project> getAll();

	public void Update(Project project);

	public void addNew(Project project);

	public Project getOne(Integer id);

	public void delete(Integer id);

	List<Project> getProjectsByUserId(Integer userId);

}
