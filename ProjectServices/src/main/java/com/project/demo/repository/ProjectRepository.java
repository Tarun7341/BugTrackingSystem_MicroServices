package com.project.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.demo.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

	List<Project> findProjectsByUserId(Integer userId);

}
