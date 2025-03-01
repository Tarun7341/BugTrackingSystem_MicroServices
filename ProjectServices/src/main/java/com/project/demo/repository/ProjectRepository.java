package com.project.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.demo.dto.ProjectIdNameProjection;
import com.project.demo.dto.UserDTO;
import com.project.demo.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

	List<Project> findProjectsByUserId(List<Integer> userId);
	
	 @Query("SELECT p.id AS id, p.name AS name FROM Project p")
	 List<ProjectIdNameProjection> findProjectIdAndName();
	 
	 
	   
	
	

}
